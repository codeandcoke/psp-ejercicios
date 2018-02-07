package com.sfaci.clientechat;

/**
 * Created by dam on 6/02/18.
 */
public class Principal {

    public static void main(String args[]) {

        ClienteChat view = new ClienteChat();
        ClienteChatController controller = new
                ClienteChatController(view);
    }
}
