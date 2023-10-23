import java.io.IOException;
import java.net.URLDecoder;
import java.net.URI;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    String message = "";
    int num = 0;

    public String handleRequest(URI url) {
        if (url.getPath().contains("/add-message")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                num++;
                if (num == 1){
                    message += String.format("%d. %s", num, parameters[1]);
                } else {
                    message += String.format("\n%d. %s", num, parameters[1]);
                }
                return message;
            }
        }
        return "404 Not Found!";
    }
}

class StringSever {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);


        Server.start(port, new Handler());
    }
}
