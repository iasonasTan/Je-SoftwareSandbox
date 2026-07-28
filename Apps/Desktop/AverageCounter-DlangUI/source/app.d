module main;

import gui : UIManager;
import logic : AverageComputer;
import dlangui;

mixin APP_ENTRY_POINT;

extern (C) int UIAppMain(string[] args) {
    UIManager.get().start(new AverageComputer());
    return Platform.instance.enterMessageLoop();
}
