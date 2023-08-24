# InjectionTemplate
Simple Java Agent Injection Template

This template uses Javassist to inject code (im too lazy to use asm).
You should have a basic knowledge of Reflection and the Minecraft code base and how Java Agents work.
You also need a mapping viewer to view the mappings of your corresponding minecraft version.

Hooks are code injections which will inject your code into a specific class.
Wrappers are helpers to get/change fields and execute methods in a class.

I have provided some examples in the code to get you going. There is also a Main class for injecting 
this Agent into a target program. If you wish to use this Template you need to put it on github and licence it under the GNU GENERAL PUBLIC LICENSE 3.0.

Please do not change the package name as it will cause issues with minecrafts default package obfuscation.

If you have questions join this discord: https://discord.gg/TsdAuxxraU
