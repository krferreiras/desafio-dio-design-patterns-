package kr.ferreiras.gof.service.impl;

import kr.ferreiras.gof.model.Cliente;
import kr.ferreiras.gof.model.ClienteRepository;
import kr.ferreiras.gof.model.Endereco;
import kr.ferreiras.gof.model.EnderecoRepository;
import kr.ferreiras.gof.service.ClienteService;
import kr.ferreiras.gof.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    // TODO Singleton: Injetar os componentes do Spring com @Autowired. (Resolvido)
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    public ViaCepService viaCepService;
    // TODO Strategy: Implementar os métodos definidos na interface. (Resolvido)
    // TODO Facade: Abstrair integrações com subsistemas, provendo uma interface simples. (Resolvido)


    @Override
    public Iterable<Cliente> buscarTodos() {
        // FIXME Buscar todos os Clientes.(Resolvido)
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        // FIXME Buscar por id.(Resolvido)
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isEmpty()){
            cliente = null;
        }
        return cliente.get();
    }

    @Override
    public void inserir(Cliente cliente) {
        salvarClienteComCep(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        // FIXME Buscar Cliente por ID, caso exista:(Resolvido)
        Optional<Cliente> clienteDB = clienteRepository.findById(id);
        if(clienteDB.isPresent()) {
            salvarClienteComCep(cliente);

        }
    }

    @Override
    public void deletar(Long id) {
        // FIXME Deletar Cliente por ID.(Resolvido)
        clienteRepository.deleteById(id);
    }

    private void salvarClienteComCep(Cliente cliente) {
        // FIXME Verificar se o endereço do cliente já existe (pelo CEP).(Resolvido)
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            // FIXME Caso não exista, integrar com o ViaCep e persistir o retorno.(Resolvido)
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        // FIXME Inserir Cliente, vinculando o Endereço (novo ou existente).(Resolvido)
        clienteRepository.save(cliente);
    }
}
