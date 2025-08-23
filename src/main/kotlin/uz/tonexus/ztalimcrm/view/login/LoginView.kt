package uz.tonexus.ztalimcrm.view.login

import com.vaadin.flow.component.UI
import com.vaadin.flow.component.login.AbstractLogin.LoginEvent
import com.vaadin.flow.component.login.LoginI18n
import com.vaadin.flow.i18n.LocaleChangeEvent
import com.vaadin.flow.i18n.LocaleChangeObserver
import com.vaadin.flow.router.Route
import com.vaadin.flow.server.VaadinSession
import io.jmix.core.CoreProperties
import io.jmix.core.MessageTools
import io.jmix.flowui.component.loginform.JmixLoginForm
import io.jmix.flowui.kit.component.ComponentUtils
import io.jmix.flowui.kit.component.loginform.JmixLoginI18n
import io.jmix.flowui.view.*
import io.jmix.securityflowui.authentication.AuthDetails
import io.jmix.securityflowui.authentication.LoginViewSupport
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import java.util.*

@Route(value = "login")
@ViewController(id = "LoginView")
@ViewDescriptor(path = "login-view.xml")
open class LoginView : StandardView(), LocaleChangeObserver {

    @Autowired
    private lateinit var coreProperties: CoreProperties

    @Autowired
    private lateinit var loginViewSupport: LoginViewSupport

    @Autowired
    private lateinit var messageTools: MessageTools

    @ViewComponent
    private lateinit var login: JmixLoginForm

    @ViewComponent
    private lateinit var messageBundle: MessageBundle

    @Value($$"${ui.login.defaultUsername:}")
    private lateinit var defaultUsername: String

    @Value($$"${ui.login.defaultPassword:}")
    private lateinit var defaultPassword: String

    private val log = LoggerFactory.getLogger(LoginView::class.java)

    @Subscribe
    fun onInit(event: InitEvent) {

        initLocales()
        initDefaultCredentials()
    }

    private fun initLocales() {
        val locales: MutableMap<Locale, String> = coreProperties.availableLocales.associateByTo(
            destination = mutableMapOf(),
            keySelector = { it },
            valueTransform = messageTools::getLocaleDisplayName
        )

        ComponentUtils.setItemsMap(login, locales);

        login.selectedLocale = VaadinSession.getCurrent().locale
    }

    private fun initDefaultCredentials() {
        if (defaultUsername.isNotBlank()) {
            login.username = defaultUsername
        }

        if (defaultPassword.isNotBlank()) {
            login.password = defaultPassword
        }
    }

    @Subscribe("login")
    fun onLogin(event: LoginEvent) {
        try {
            loginViewSupport.authenticate(
                AuthDetails.of(event.username, event.password)
                    .withLocale(login.selectedLocale)
                    .withRememberMe(login.isRememberMe)
            )
        } catch (e: Exception) {
            log.warn("Login failed for user '{}': {}", event.username, e.toString())
            event.source.isError = true
        }
    }

    override fun localeChange(event: LocaleChangeEvent) = with(messageBundle) {
        UI.getCurrent().page.setTitle(getMessage("LoginView.title"))

        val loginI18n: JmixLoginI18n = JmixLoginI18n.createDefault().apply {
            form = JmixLoginI18n.JmixForm().apply {
                title = getMessage("loginForm.headerTitle")
                username = getMessage("loginForm.username")
                password = getMessage("loginForm.password")
                submit = getMessage("loginForm.submit")
                forgotPassword = getMessage("loginForm.forgotPassword")
                rememberMe = getMessage("loginForm.rememberMe")
            }

            errorMessage = LoginI18n.ErrorMessage().apply {
                title = getMessage("loginForm.errorTitle")
                message = getMessage("loginForm.badCredentials")
                username = getMessage("loginForm.errorUsername")
                password = getMessage("loginForm.errorPassword")
            }
        }

        login.setI18n(loginI18n)
    }
}
