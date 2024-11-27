package com.timur_ufanet.pool.service;

import com.timur_ufanet.pool.entity.Client;
import com.timur_ufanet.pool.exception.ResourceNotFoundException;
import com.timur_ufanet.pool.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    // Получение всех клиентов
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    // Получение клиента по ID
    public Client getClientById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Клиент не найден с id: " + id));
    }

    // Добавление нового клиента
    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    // Обновление данных клиента
    public Client updateClient(Client updatedClient) {
        Client existingClient = getClientById(updatedClient.getId());
        existingClient.setName(updatedClient.getName());
        existingClient.setPhone(updatedClient.getPhone());
        existingClient.setEmail(updatedClient.getEmail());
        return clientRepository.save(existingClient);
    }
}
