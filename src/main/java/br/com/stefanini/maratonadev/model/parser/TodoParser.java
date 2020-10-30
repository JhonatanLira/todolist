package br.com.stefanini.maratonadev.model.parser;

import br.com.stefanini.maratonadev.dto.TodoDto;
import br.com.stefanini.maratonadev.model.Todo;

public class TodoParser {

	public static TodoParser get() {
		return new TodoParser();
	}

	public Todo entidade(TodoDto dto) {
		Todo entidade = new Todo();
		entidade.setDataCriacao(dto.getDataCriacao());
		entidade.setId(dto.getId());
		entidade.setNome(dto.getNome());

		return entidade;
	}

	public TodoDto dto(Todo entidade) {
		TodoDto dto = new TodoDto();

		dto.setDataCriacao(entidade.getDataCriacao());
		dto.setId(entidade.getId());
		dto.setNome(entidade.getNome());

		return dto;
	}

}
