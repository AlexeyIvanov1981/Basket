package ru.home;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.home.startupsettings.StartupSettings;
import ru.home.startupsettings.StartupSettingsLoad;
import ru.home.startupsettings.StartupSettingsLog;
import ru.home.startupsettings.StartupSettingsSave;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final String STARTUP_SETTINGS_FILENAME = "shop.xml";

    public static void main(String[] args) {
        final StartupSettings startupSettings = readStartupSettings(new File(STARTUP_SETTINGS_FILENAME));
        final StartupSettingsLoad startupSettingsLoad = startupSettings.getStartupSettingsLoad();
        final StartupSettingsSave startupSettingsSave = startupSettings.getStartupSettingsSave();
        final StartupSettingsLog startupSettingsLog = startupSettings.getStartupSettingsLog();

        Basket basket = loadBasket(startupSettingsLoad);
        ClientLog clientLog = new ClientLog();

        while (true) {
            System.out.println("Введите товар и количество или введите 'end'");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.equals("end")) {
                basket.printCart();
                break;
            } else {
                String[] parts = input.split(" ");
                final int productNum = Integer.parseInt(parts[0]);
                final int productAmount = Integer.parseInt(parts[1]);

                basket.addToCart(productNum, productAmount);
                saveBasketIfNeeded(basket, startupSettingsSave);

                clientLog.log(productNum, productAmount);
            }
        }

        if (startupSettingsLog.getEnabled()) {
            clientLog.exportAsCSV(new File(startupSettingsLog.getFilename()));
        }
    }

    private static StartupSettings readStartupSettings(File startupSettingsFile) {
        XmlMapper xmlMapper = new XmlMapper();
        try {
            return xmlMapper.readValue(
                    new File("shop.xml"),
                    new TypeReference<>() {
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Basket loadBasket(StartupSettingsLoad startupSettingsLoad) {
        final boolean shouldLoadFromFile = startupSettingsLoad.getEnabled();
        final String loadFilename = startupSettingsLoad.getFilename();
        final StartupSettings.FileFormat loadFileFormat = startupSettingsLoad.getFileFormat();

        Basket basket;
        if (shouldLoadFromFile) {
            if (loadFileFormat.equals(StartupSettings.FileFormat.FILE_FORMAT_JSON)) {
                basket = Basket.loadBasketFromJsonFile(new File(loadFilename));
            } else if (loadFileFormat.equals(StartupSettings.FileFormat.FILE_FORMAT_TXT)) {
                basket = Basket.loadFromTxtFile(new File(loadFilename));
            } else {
                throw new RuntimeException("Unknown load file format.");
            }
        } else {
            basket =
                    new Basket(
                            new String[]{"Хлеб", "Молоко", "Масло"},
                            new int[]{30, 60, 90}
                    );
        }

        return basket;
    }

    private static void saveBasketIfNeeded(Basket basket, StartupSettingsSave startupSettingsSave) {
        final boolean shouldSaveToFile = startupSettingsSave.getEnabled();
        final String saveFilename = startupSettingsSave.getFilename();
        final StartupSettings.FileFormat saveFileFormat = startupSettingsSave.getFileFormat();

        if (shouldSaveToFile) {
            if (saveFileFormat.equals(StartupSettings.FileFormat.FILE_FORMAT_JSON)) {
                basket.saveBasketToJsonFile(new File(saveFilename));
            } else if (saveFileFormat.equals(StartupSettings.FileFormat.FILE_FORMAT_TXT)) {
                basket.saveBasketToTextFile(new File(saveFilename));
            } else {
                throw new RuntimeException("Unknown save file format.");
            }
        }
    }
}
