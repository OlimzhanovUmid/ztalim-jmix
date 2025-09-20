package uz.tonexus.ztalimcrm.extensions

import io.jmix.flowui.app.inputdialog.DialogOutcome
import io.jmix.flowui.app.inputdialog.InputDialog

val InputDialog.InputDialogCloseEvent.isOk: Boolean
    get() = closedWith(DialogOutcome.OK)

val InputDialog.InputDialogCloseEvent.isYes: Boolean
    get() = closedWith(DialogOutcome.YES)

val InputDialog.InputDialogCloseEvent.isNo: Boolean
    get() = closedWith(DialogOutcome.NO)

val InputDialog.InputDialogCloseEvent.isCancel: Boolean
    get() = closedWith(DialogOutcome.CANCEL)
