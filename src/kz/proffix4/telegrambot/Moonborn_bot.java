package kz.proffix4.telegrambot;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

class MyTelegramBot extends TelegramLongPollingBot {

    // Метод получения команд бота, тут ничего не трогаем
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(update.getMessage().getChatId());
            sendMessage.setText(doCommand(update.getMessage().getChatId(),
                    update.getMessage().getText()));
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
            }
        }
    }

    // Тут задается нужное значение имени бота
    @Override
    public String getBotUsername() {
        return "SamiyDersky_bot";
    }

    // Тут задается нужное значение токена
    @Override
    public String getBotToken() {
        return "1084936058:AAGmLalrTAb5SVWxTBSQsify1mb7R1XtfO0";
    }

    // Метод обработки команд бота
    public String doCommand(long chatId, String command) {
        if (command.startsWith("/bar1")) {
            try {
                sendPhoto(new SendPhoto().setChatId(chatId).setNewPhoto(new File("btc.png")));
            } catch (TelegramApiException e) {
            }
            String[] param = command.split(" ");
            if (param.length > 1) { 
                return "Добрый день я бот" + getResh(Integer.parseInt(param[1]),Integer.parseInt(param[2]),Integer.parseInt(param[3]),Integer.parseInt(param[4]),Integer.parseInt(param[5]));
            } else {
         return "Используйте команду /bar1 и введите значение a b d x";
            }
        }
        return "Используйте команду /bar1 и введите значение a b d x";
    }

    private String getResh( int a, int b,int c, int d, int x) {
        StringBuilder ans = new StringBuilder();
        double y=0;
        if (x < 5)
            y = ((a*a)*c+(b*b)-d)/x ;
        else 
            y =  (x*x)+5;
        ans.append(y);
        return ans.toString();
    }
}
public class Moonborn_bot {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            // ЗАПУСКАЕМ КЛАСС НАШЕГО БОТА
            botsApi.registerBot(new MyTelegramBot());
        } catch (TelegramApiException e) {
        }
    }

}
