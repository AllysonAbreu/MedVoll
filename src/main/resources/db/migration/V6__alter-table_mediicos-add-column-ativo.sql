-- Adiciona a coluna 'ativo' na tabela 'medicos'
ALTER TABLE medicos ADD COLUMN ativo boolean;

-- Altera a coluna 'ativo' na tabela 'pacientes' para não permitir valores nulos
ALTER TABLE pacientes ALTER COLUMN ativo SET NOT NULL;
