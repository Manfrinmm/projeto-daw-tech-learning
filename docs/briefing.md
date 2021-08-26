# Plataforma de Curso (Tech learning)

## Dados

### Adm

- nome;
- email;
- senha;

### Aluno

- nome;
- cpf;
- email;
- senha;

### Instrutor

- nome;
- cpf;
- email;

### Curso

- Nome;
- Descrição;
- Imagem (capa/link);
- `Instrutor`;

### Aula

- Nome;
- Vídeo (link);
- `Curso`;

### AlunoCurso (Relacionamento)

- `Aluno`
- `Curso`

## Relacionamentos

- Curso:
  - N:1 `Instrutor`
- Aula:
  - N:1 `Curso`
- AlunoCurso:
  - N:N `Aluno` e `Curso`

## Regra de negócios

### Admin

- CRUD Instrutores;
- CRUD Cursos;
- RUD Alunos;

### Aluno

- Listar cursos;
- Visualizar um curso (Aula);
- Se inscrever em um curso.
- Não poder visualizar um curso em que não esteja inscrito.
