package de.der_riddler.game;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Util {

    public static TimerTask makeTimerTask(Runnable runnable) {
        return new TimerTask() {
            @Override
            public void run() {
                runnable.run();
            }
        };
    }

    public static <T extends Named> void putAll(Map<String, T> map, List<T> list) {
        map.putAll(Util.mapByName(list));
    }

    public static <T extends Named> Map<String, T> mapByName(Collection<T> collection) {
        return collection
                .stream()
                .collect(
                        Collectors.toMap(Named::getName, Function.identity())
                );
    }

    public static String getMD5Hash(String string) {
        try {
            final MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(string.getBytes());
            final byte[] hashBytes = md.digest();
            final StringBuilder stringBuilder = new StringBuilder();
            for (byte b : hashBytes)
                stringBuilder.append(String.format("%02x", b));
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getUsername() {
        return System.getProperty("user.name");
    }

    public static String getHostname() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            return "GamingPC";
        }
    }

    public static String getRandomBytes(int length) {
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++)
            builder.append(String.format("%02x", (int) (Math.random() * 256)));
        return builder.toString();
    }
}
