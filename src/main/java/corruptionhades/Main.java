package corruptionhades;

import com.sun.tools.attach.VirtualMachine;

public class Main {

    public static void main(String[] args) throws Exception {

        String pid = "YOUR PROCESS ID HERE";
        String path = "PATH TO YOUR BUILD VERSION OF THIS AGENT";

        VirtualMachine vm = VirtualMachine.attach(pid);
        vm.loadAgent(path);
    }
}
