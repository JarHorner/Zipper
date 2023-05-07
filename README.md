# Zipper
A small application that zips and unzips.
I started this project to recall my Java knowledge and use certain technologies I have never used before.

## Usage

By using Maven, Once the code is cloned, the program should run fine right away. if not, make sure to validate, then compile using Maven.

Can be exported into a .jar file. which runs using the command: `java -jar {path-of-jar}`

To make it run as a .exe, I used [Launch4j](https://launch4j.sourceforge.net/) which is a Cross-platform Java executable wrapper what allows the program to run as an application.

if you want to make the application run on any machine on windows, even without java installed:

1. take that .exe file built with Launch4j or any other Java executable wrapper and a file with a jdk version, and archive them into a .7z file (i used [7zip](https://www.7-zip.org/)). 
2. You can change the name and change the compession level, but **ensure to keep the Archive format 7z**. all other default settings are good.
3. Install the application [7-Zip SFX Maker](https://sourceforge.net/projects/sfx-maker/), a windows GUI for creating self-extracting (*.exe) files from 7zip archives (*.7z). This program requires .NET Framework 2.0 or higher to run.
4. Make sure to run the program as administrator to avoid small bugs in newer operating systems. the error about Resource Hacker is not vital for operation, only if you want to change the icon and metadata of the application.
5. Add the .7z file to the `Files` section. 
6. Under the `Dialogs` section, in `General` you can change the title and select `Extract to temporary folder`. In `ExtractPath` un-select `Allow user to change path`. In `Progress` you can change text if you want. 
7. Under the `Tasks` section, add a new task by selecting the big **+** button, then select `Run Program`. In this menu, in the `Program` input bar, at the end, add the name of the .exe file **MAKE SURE TO ADD THE .exe EXTENTION AT THE END**. Select `ok`.
8. You can now select the `Make SFX` button at the bottom, and it will make the application.


## Acknowledgements

This project uses JavaFX, an open source client application platform for desktop, mobile and embedded systems built on Java with the goal of producing a modern, 
efficient, and fully featured toolkit for developing rich client applications.

It also incorporates Apache Maven, a software project management and comprehension tool I though would be useful to learn.

 - [JavaFX](https://openjfx.io/)
 - [Apache Maven](https://maven.apache.org/)

programs used to make this into an application:

 - [Launch4j](https://launch4j.sourceforge.net/)
 - [7zip](https://www.7-zip.org/)
 - [7-Zip SFX Maker](https://sourceforge.net/projects/sfx-maker/)
 
## Appendix

I started this project to recall my Java knowledge and use certain technologies I have never used before.
