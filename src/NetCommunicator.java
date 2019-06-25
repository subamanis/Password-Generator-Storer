import java.awt.Desktop;
import java.io.PrintStream;
import java.net.URI;

public class NetCommunicator {
    public static void openWebPage(int index) {
        Desktop d = Desktop.getDesktop();
        try {
            switch (index) {
                case 0: {
                    System.out.println("Opening Origin...");
                    d.browse(new URI("https://www.origin.com/irl/en-us/store"));
                    break;
                }
                case 1: {
                    System.out.println("Opening Uplay...");
                    d.browse(new URI("https://uplay.ubisoft.com/en-GB"));
                    break;
                }
                case 2: {
                    System.out.println("Opening your Gmail...");
                    d.browse(new URI("https://mail.google.com/mail/u/0/#inbox"));
                    break;
                }
                case 3: {
                    System.out.println("Opening Steam...");
                    d.browse(new URI("https://store.steampowered.com"));
                    break;
                }
                case 4: {
                    System.out.println("Opening Paysafe...");
                    d.browse(new URI("https://my.paysafecard.com/mypins-psc/"));
                    break;
                }
                case 5: {
                    System.out.println("Opening PayPal...");
                    d.browse(new URI("https://www.paypal.com/us/signin"));
                    break;
                }
                case 6: {
                    System.out.println("Opening GitHub...");
                    d.browse(new URI("https://github.com"));
                    break;
                }
                case 7: {
                    System.out.println("Opening your Mi Account...");
                    d.browse(new URI("https://account.xiaomi.com/pass/serviceLogin"));
                    break;
                }
                case 8: {
                    System.out.println("Opening Facebook...");
                    d.browse(new URI("https://www.facebook.com"));
                    break;
                }
                case 9: {
                    System.out.println("Opening Instagram...");
                    d.browse(new URI("https://www.instagram.com/?hl=el"));
                    break;
                }
                case 10: {
                    System.out.println("Opening GOG Galaxy...");
                    d.browse(new URI("https://www.gog.com/galaxy"));
                    break;
                }
                case 11: {
                    System.out.println("Opening your router...");
                    d.browse(new URI("http://192.168.2.1"));
                    break;
                }
                case 12: {
                    System.out.println("Opening LinkedIn...");
                    d.browse(new URI("https://www.linkedin.com/m/login/"));
                    break;
                }
                case 13: {
                    System.out.println("Opening Twitch...");
                    d.browse(new URI("https://www.twitch.tv"));
                    break;
                }
                case 14: {
                    System.out.println("Opening BitBucket...");
                    d.browse(new URI("https://id.atlassian.com/login"));
                    break;
                }
                case 15: {
                    System.out.println("Opening Mega.nz...");
                    d.browse(new URI("https://mega.nz/fm/8QMSkajS"));
                    break;
                }
                case 16: {
                    System.out.println("Opening Epic Games Store...");
                    d.browse(new URI("https://www.epicgames.com/store/en-US/"));
                    break;
                }
                default: {
                    System.out.println("No suitable web page found");
                    break;
                }
            }
        }
        catch (Exception e) {
            System.out.println(e + "occurred while trying to open a URL");
        }
    }
}