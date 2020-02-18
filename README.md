# Password-Generator-Storer
This is a password-generator/storer that I created for fun/personal use. It utilises Java's built in "AES/CBC/PKCS5P" for the encryption of the passwords and the Java implementation of BCrypt (https://www.mindrot.org/projects/jBCrypt/) for hashing the general password of the program.

Run from cmd with "src" as root folder:</br>
javac manager/*.java manager/io/*.java  manager/logic/*.java  manager/security/*.java org/mindrot/jbcrypt/*.java </br>
java manager.Main

<b>Note</b> that if you try to run the code from an IDE you will probably have to replace the "console.readPassword()" commands with Scanner.
