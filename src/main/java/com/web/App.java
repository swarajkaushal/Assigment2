package com.web;

import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

 class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello this is weather forecasting app" );
    	Scanner sc = new Scanner(System.in);
        HttpClient httpClient = HttpClients.createDefault();
        String apiUrl = "https://samples.openweathermap.org/data/2.5/forecast/hourly?q=London,us&appid=b6907d289e10d714a6e88b30761fae22";

        while (true) {
            System.out.println("choose weather option:");
            System.out.println("1. Get Temperature");
            System.out.println("2. Get Wind-Speed");
            System.out.println("3. Get Pressure");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = sc.nextInt();
            sc.nextLine(); 
            switch (choice) {
            case 1:
                System.out.print("Enter date with time (yyyy-MM-dd HH:mm:ss): ");
                String Date = sc.nextLine();
                getTemperature(httpClient, apiUrl, Date);
                break;
            case 2:
                System.out.print("Enter date with time (yyyy-MM-dd HH:mm:ss): ");
                String Date2 = sc.nextLine();
                getWindSpeed(httpClient, apiUrl, Date2);
                break;
            case 3:
                System.out.print("Enter date with time (yyyy-MM-dd HH:mm:ss): ");
                String Date3 = sc.nextLine();
                getPressure(httpClient, apiUrl, Date3);
                break;
            case 0:
                System.out.println("Exit");
                System.exit(0);
            default:
                System.out.println("!Invalid choice  Please try again.");
        }
    }
}
    
    private static void getTemperature(HttpClient httpClient, String apiUrl, String inputDate) {
        try {
            HttpGet httpGet = new HttpGet(apiUrl);
            HttpEntity entity = httpClient.execute(httpGet).getEntity();
            String content = EntityUtils.toString(entity);

            JSONObject jsonObject = new JSONObject(content);
            JSONArray list = jsonObject.getJSONArray("list");

            for (int i = 0; i < list.length(); i++) {
                JSONObject data = list.getJSONObject(i);
                String dateStr = data.getString("dt_txt");

                if (dateStr.equals(inputDate)) {
                    JSONObject main = data.getJSONObject("main");
                    double temperature = main.getDouble("temp");
                    System.out.println("Temperature at " + inputDate + ": " + temperature + "°C");
                    return;
                }
            }
            System.out.println("No data found for the given date and time.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void getWindSpeed(HttpClient httpClient, String apiUrl, String inputDate) {
        try {
            HttpGet httpGet = new HttpGet(apiUrl);
            HttpEntity entity = httpClient.execute(httpGet).getEntity();
            String content = EntityUtils.toString(entity);

            JSONObject jsonObject = new JSONObject(content);
            JSONArray list = jsonObject.getJSONArray("list");

            for (int i = 0; i < list.length(); i++) {
                JSONObject data = list.getJSONObject(i);
                String dateStr = data.getString("dt_txt");

                if (dateStr.equals(inputDate)) {
                    JSONObject wind = data.getJSONObject("wind");
                    double windSpeed = wind.getDouble("speed");
                    System.out.println("Wind Speed at " + inputDate + ": " + windSpeed + " m/s");
                    return;
                }
            }
            System.out.println("No data found for the given date and time.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getPressure(HttpClient httpClient, String apiUrl, String inputDate) {
        try {
            HttpGet httpGet = new HttpGet(apiUrl);
            HttpEntity entity = httpClient.execute(httpGet).getEntity();
            String content = EntityUtils.toString(entity);

            JSONObject jsonObject = new JSONObject(content);
            JSONArray list = jsonObject.getJSONArray("list");

            for (int i = 0; i < list.length(); i++) {
                JSONObject data = list.getJSONObject(i);
                String dateStr = data.getString("dt_txt");
                if (dateStr.equals(inputDate)) {
                    JSONObject main = data.getJSONObject("main");
                    double pressure = main.getDouble("pressure");
                    System.out.println("Pressure at " + inputDate + ": " + pressure + " hPa");
                    return;
                }
            }

            System.out.println("No data found for the given date and time.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        
    }

