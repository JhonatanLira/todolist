package br.com.stefanini.maratonadev.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;

import org.eclipse.microprofile.opentracing.Traced;

import br.com.stefanini.maratonadev.dao.TodoDao;
import br.com.stefanini.maratonadev.dto.TodoDto;
import br.com.stefanini.maratonadev.model.Todo;
import br.com.stefanini.maratonadev.model.parser.TodoParser;

@RequestScoped
@Traced
public class TodoService {

	@Inject
	TodoDao dao;

	private void validar(Todo todo) {
		if (todo.getNome() == null) {
			throw new NotFoundException();
		}
	}

	@Transactional(rollbackOn = Exception.class)
	public void inserir(TodoDto todoDto) {
		Todo todo = TodoParser.get().entidade(todoDto);
		validar(todo);
		todo.setDataCriacao(LocalDateTime.now());
		dao.inserir(todo);

	}

	public List<TodoDto> listar() {
		return dao.listar().stream().map(TodoParser.get()::dto).collect(Collectors.toList());
	}

	
	public void excluir(Long id) {
		dao.excluir(id);
	}

}
