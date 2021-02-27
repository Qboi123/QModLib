# QModLib
MC Modding Library for Qboi123's Mod(s).
   
## CurseForge
https://www.curseforge.com/minecraft/mc-mods/qmodlib  
  
## Mods that uses this library:
 * [QForgeMod](https://www.curseforge.com/minecraft/mc-mods/qforgemod)

## Using as API
1) Set environment variables for your OS. ([Windows](https://www.tenforums.com/tutorials/121855-edit-user-system-environment-variables-windows.html), [Linux](https://www.serverlab.ca/tutorials/linux/administration-linux/how-to-set-environment-variables-in-linux/), [Mac](https://medium.com/@himanshuagarwal1395/setting-up-environment-variables-in-macos-sierra-f5978369b255#:~:text=If%20the%20environment%20variable%20you,variable%20name%20and%20its%20value.))  
   Add one with the name `GITHUB_USERNAME` and the value as your github username  
   Add one with the name `GITHUB_TOKEN` and the value as your [token](https://github.com/settings/tokens).
2) Add the repository (`https://maven.pkg.github.com/Qboi123/QForgeMod`)
   ```gradle
   repositories {
       maven {
           name = "GitHubPackages"
           url = uri("https://maven.pkg.github.com/Qboi123/QForgeMod")
           credentials {
               username = System.getenv("GITHUB_USERNAME")
               password = System.getenv("GITHUB_TOKEN")
           }
       }
   }
   ```
3) Add the dependencies (`com.qsoftware:qforgemod`, `com.qsoftware:qmodlib`)
   ```gradle
   dependencies {
       // Other dependencies here. //
       
       compileOnly( fg.deobf(group: "com.qsoftware", name: "qmodlib", version: "1.0.+")) {
           exclude group: "net.minecraftforge", name: "forge", version: "+"
           exclude group: "net.minecraftforge", name: "forge", version: "+", classifier: "launcher"
       }
       runtimeOnly( fg.deobf(group: "com.qsoftware", name: "qmodlib", version: "1.0.+")) {
           exclude group: "net.minecraftforge", name: "forge", version: "+"
           exclude group: "net.minecraftforge", name: "forge", version: "+", classifier: "launcher"
       }

       // Other dependencies here. //
   }
   ```
6) Reload gradle.
7) You're done!
