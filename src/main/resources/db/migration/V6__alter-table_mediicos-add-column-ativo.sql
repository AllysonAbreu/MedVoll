-- Adiciona a coluna 'ativo' na tabela 'medicos'
ALTER TABLE medicos ADD COLUMN ativo INT;

-- Atualiza todos os registros da tabela 'medicos' para definir 'ativo' como 1
UPDATE medicos SET ativo = 1;

-- Altera a coluna 'ativo' na tabela 'pacientes' para não permitir valores nulos
ALTER TABLE pacientes ALTER COLUMN ativo SET NOT NULL;
