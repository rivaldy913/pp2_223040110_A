package dao;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.management.relation.Relation;

import model.Pendaftaran;

public class PendaftaranDAO {
    private static final String FILE_NAME = "registrations.dat";

    // Method to save the list of registrations to a file
    public void saveRegistrations(List<Relation> registrations) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(registrations);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to load the list of registrations from a file
    public List<Relation> loadRegistrations() {
        List<Relation> registrations = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        return registrations;
    }

    // Method to add a registration
    public void addRegistration(Relation registration) {
        List<Relation> registrations = loadRegistrations();
        registrations.add(registration);
        saveRegistrations(registrations);
    }

    // Method to delete a registration by index
    public void deleteRegistration(int index) {
        List<Relation> registrations = loadRegistrations();
        if (index >= 0 && index < registrations.size()) {
            registrations.remove(index);
            saveRegistrations(registrations);
        }
    }

    public List<Pendaftaran> getAllPendaftarans() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllPendaftarans'");
    }

    public void updatePendaftaran(int selectedPendaftaranId, String name, String address, String gender,
            String membership, String interests, boolean subscribed, Date registrationDate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatePendaftaran'");
    }

    public static void deletePendaftaran(int pendaftaranId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletePendaftaran'");
    }

    public void addPendaftaran(Pendaftaran pendaftaran) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addPendaftaran'");
    }
}
