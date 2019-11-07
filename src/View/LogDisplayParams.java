package View;

import javafx.scene.control.CheckBox;

public class LogDisplayParams {

    private CheckBox showTime;
    private CheckBox showPrivateMessages;
    private CheckBox showGlobalMessages;
    private CheckBox showLocalMessages;

    public LogDisplayParams(CheckBox showTime, CheckBox showPrivateMessages, CheckBox showGlobalMessages, CheckBox showLocalMessages) {
        this.showTime = showTime;
        this.showPrivateMessages = showPrivateMessages;
        this.showGlobalMessages = showGlobalMessages;
        this.showLocalMessages = showLocalMessages;
    }

    public boolean isShowTime() {
        return showTime.isSelected();
    }

    public boolean isShowPrivateMessages() {
        return showPrivateMessages.isSelected();
    }

    public boolean isShowGlobalMessages() {
        return showGlobalMessages.isSelected();
    }

    public boolean isShowLocalMessages() {
        return showLocalMessages.isSelected();
    }
}
