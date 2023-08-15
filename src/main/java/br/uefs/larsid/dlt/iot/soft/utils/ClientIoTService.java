package br.uefs.larsid.dlt.iot.soft.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientIoTService {

  private static int HTTP_SUCCESS = 200;
  private static final Logger logger = Logger.getLogger(ClientIoTService.class.getName());

  /**
   * Requests devices that are connected through the API.
   *
   * @param urlAPI String - API Url.
   * @return String
   */
  public static String getApiIot(String urlAPI) {
    try {
      URL url = new URL(urlAPI);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();

      if (conn.getResponseCode() != HTTP_SUCCESS) {
        throw new RuntimeException(
          "HTTP error code : " + conn.getResponseCode()
        );
      }

      BufferedReader br = new BufferedReader(
        new InputStreamReader((conn.getInputStream()))
      );

      String temp = null;
      String devicesJson = null;

      while ((temp = br.readLine()) != null) {
        devicesJson = temp;
      }

      conn.disconnect();

      return devicesJson;
    } catch (MalformedURLException e) {
      logger.log(Level.SEVERE, null, e);
    } catch (IOException e) {
      logger.log(Level.SEVERE, null, e);
    }

    return null;
  }
}