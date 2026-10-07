package com.codegnan.vehicleparkingmanagement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private static final Path DATA_FOLDER =
            Paths.get("data");

    private static final Path ENTRIES_FILE =
            DATA_FOLDER.resolve("entries.txt");

    private static final Path REPORT_FILE =
            DATA_FOLDER.resolve("report.txt");


    public static void createDataFolder() throws IOException {

        Files.createDirectories(DATA_FOLDER);
    }


    public static void saveEntries(List<ParkingEntry> entries)
            throws IOException {

        createDataFolder();

        try (BufferedWriter writer =
                     Files.newBufferedWriter(
                             ENTRIES_FILE,
                             StandardCharsets.UTF_8)) {

            for (ParkingEntry entry : entries) {

                writer.write(
                        entry.getId() + "|" +
                        entry.getVehicleNumber() + "|" +
                        entry.getVehicleType() + "|" +
                        entry.getSlotNumber() + "|" +
                        entry.getStatus()
                );

                writer.newLine();
            }
        }
    }


    public static List<ParkingEntry> loadEntries()
            throws IOException {

        createDataFolder();

        List<ParkingEntry> entries =
                new ArrayList<>();

        if (!Files.exists(ENTRIES_FILE)) {
            return entries;
        }

        try (BufferedReader reader =
                     Files.newBufferedReader(
                             ENTRIES_FILE,
                             StandardCharsets.UTF_8)) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] fields =
                        line.split("\\|", -1);


                if (fields.length != 5) {

                    throw new IOException(
                            "Invalid record in entries.txt"
                    );
                }


                int id =
                        Integer.parseInt(fields[0]);


                String vehicleNumber =
                        fields[1];


                String vehicleType =
                        fields[2];


                int slotNumber =
                        Integer.parseInt(fields[3]);


                ParkingStatus status =
                        ParkingStatus.valueOf(fields[4]);


                ParkingEntry entry;


                if (vehicleType.equals("TwoWheeler")) {

                    entry = new TwoWheelerEntry(
                            id,
                            vehicleNumber,
                            slotNumber,
                            status
                    );

                } else if (vehicleType.equals("FourWheeler")) {

                    entry = new FourWheelerEntry(
                            id,
                            vehicleNumber,
                            slotNumber,
                            status
                    );

                } else {

                    throw new IOException(
                            "Invalid vehicle type in entries.txt"
                    );
                }


                entries.add(entry);
            }
        }


        return entries;
    }


    public static void saveReport(String report)
            throws IOException {

        createDataFolder();

        try (BufferedWriter writer =
                     Files.newBufferedWriter(
                             REPORT_FILE,
                             StandardCharsets.UTF_8)) {

            writer.write(report);
        }
    }
}