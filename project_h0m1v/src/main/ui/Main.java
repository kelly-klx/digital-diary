package ui;

import model.Event;
import model.EventLog;

//Received info on event logging from https://www.baeldung.com/jvm-shutdown-hooks,
// https://stackoverflow.com/questions/8722826/when-do-i-need-to-call-this-method-runtime-getruntime-addshutdownhook
// https://www.tutorialspoint.com/java/lang/runtime_addshutdownhook.htm
//Runs diary application
public class Main {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                for (Event next : EventLog.getInstance()) {
                    System.out.println(next);
                }
            }
        });
        new DiaryEditor();
    }
}