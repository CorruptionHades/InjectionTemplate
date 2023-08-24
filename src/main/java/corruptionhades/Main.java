package corruptionhades;

import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws Exception {

        String pid = "YOUR PROCESS ID HERE";
        String path = "PATH TO YOUR BUILT VERSION OF THIS AGENT";

        VirtualMachine vm = VirtualMachine.attach(pid);
        vm.loadAgent(path);
    }
}
