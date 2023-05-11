# Hotel Alura

### Um programa completo de reservas para hotel.
Com ele poderá registrar reservas para diferentes tipos de habitação, com diferentes preços por dia, podendo escolher entre diferentes tipos de forma de pagamento. Também poderá buscar hospedes pelo seu sobrenome e reservas pelo seu código, podendo modificar ou eliminar.

### O sistema também conta com dois módulos extras.
#### Um, donde poderá administrar as tabelas auxiliares do programa, criando, modificando ou eliminando registros de:
###### Usuários- Aqui configura os usuráios com login, senha e nível de acesso dentro do programa.
###### Níveis de acesso- Determina quais ações um determinado usuário poderá realizar.
###### Diárias- Aqui poderá determinar os preços das diárias por dia e por tipo de habitação.
###### Tipos de habitação- Configura os diferentes tipos de habitação que possui o hotel (Single, Double, Triplo, Executiva, Presidencial, etc.).
###### Formas de pagamento- Configura as diferentes formas de pagamento aceitas pelo hotel.
###### Nacionalidades- Aqui uma tabela para padronizar as diferentes nacionalidades que um hospede pode ter.
#### Dois, donde poderá aceder a diferentes listados gerenciais e ações gerenciais.
###### Arrecadação por período- Dado um determinado período de tempo, saiba qual foi a arrecadação do hotel.
###### Lista de de apagadas- Saiba quais reservas foram apagadas num período e determine se quer recupera-la ou elimina-la definitivamente.

![image](https://github.com/jnoya/hotelAlura/assets/109557381/950aaf4d-6508-4704-bbdd-bb6be8d90f74)
   
   #### Tecnologias utilizadas no desenvolvimento.
  ###### Java 8 +,
  ###### MySql 8,
  ###### Java JDBC

![image](https://github.com/jnoya/hotelAlura/assets/109557381/ffce8d5c-f926-446d-9de4-186111efc34a)

Insira um nome de usuário e uma senha válida e comece a utilizar o sistema. Ao clicar no botão entrar, o programa verificará se suas credenciais são válidas e qual o seu nível de acesso no sistema.

![image](https://github.com/jnoya/hotelAlura/assets/109557381/5350288a-0367-43e7-8491-10de86bd8622)
   
Aqui pode escolher segundo o seu nível de acesso entre ir para o registro de reservas, buscar um cliente ou uma reserva ou aceder ao modulo administrativo.

![image](https://github.com/jnoya/hotelAlura/assets/109557381/14d1892d-7252-4921-b157-fbe562608fb0)

Aqui escolhe o tipo de habitação o período e o sistema automaticamente lhe dará  o valor total da reserva, logo escolhe a forma de pagamento e com o botão próximo passa para o registro do cliente que fez a reserva.

![image](https://github.com/jnoya/hotelAlura/assets/109557381/cf104b71-f45e-455f-a515-e09f0d8f1648)

Aqui no registro do hospede a tela já vem com o código da reserva preenchido (o código é formado por um prefixo "RALura" mais um valor ramdômico de números e letras de 5 espaços mais o número de reserva no banco de dados). Poderá preencher os dados do hospede se for um novo cliente ou escolher um já existente em nosso banco de dados com o botão buscar.

![image](https://github.com/jnoya/hotelAlura/assets/109557381/9199f10f-bbe9-4a23-85e8-5181ddcad03a)

Aqui poderá buscar por sobrenome, telefone ou data de nascimento e se o cliente já existir escolher e carregar os dados automaticamente na reserva.
Uma vez concluído o sistema nos dará uma mensagem com o resultado da operação.

![image](https://github.com/jnoya/hotelAlura/assets/109557381/bc8e9635-a337-4340-ac6f-5bc865072082)

O sistema de busca nos permite encontrar um hospede por seu sobrenome ou uma reserva pelo seu código de reserva. Uma vez achado, poderá modificar ou eliminar o registro selecionado.

![image](https://github.com/jnoya/hotelAlura/assets/109557381/691e1d89-61cd-4e0a-888e-e3cfac7560a4)

No modulo administrativo poderá escolher entre ir para a administração das tabelas auxiliares ou aceder aos listados gerenciais.

![image](https://github.com/jnoya/hotelAlura/assets/109557381/fa342b4b-1d37-4e0b-84f1-28fe391edc69)

Em listados conseguirá saber qual foi o valor total arrecadado num período ou se alguma reserva foi apagada num periodo e se sim poder recupera-la ou elimina-la definitivamente. Isso é possível porque no modulo de busca tanto reserva como hospede em caso de serem apagados só serão apagados de forma lógica, cabendo ao nível gerencial decidir por fazer uma eliminação definitiva ou não.

![image](https://github.com/jnoya/hotelAlura/assets/109557381/a6822fde-ff43-4162-8981-6f27c252cf5a)

Na administração das tabelas auxiliares poderá dar alta, dar de baixa ou modificar as diferentes tabelas.

![image](https://github.com/jnoya/hotelAlura/assets/109557381/06fd7dac-b039-4b72-ab58-de841b6ded81)

Níveis de acesso com um código e uma descrição.

![image](https://github.com/jnoya/hotelAlura/assets/109557381/c5c7b566-00c0-4520-a11e-c64c379631dc)

Usuários com código, nome de usuário, senha e nível de acesso.

![image](https://github.com/jnoya/hotelAlura/assets/109557381/e21ed825-a42d-4081-9814-7fdf030c6131)

Diárias com data, tipo de habitação e preço.

![image](https://github.com/jnoya/hotelAlura/assets/109557381/0ae3483b-deaf-4ef9-8d9d-70d36d5b1ca6)

Forma de pagamento com código e forma de pagamento.

![image](https://github.com/jnoya/hotelAlura/assets/109557381/6116ecb4-2074-485d-8890-b3b7a1c3889a)

Tipo de habitação com código e descrição.

![image](https://github.com/jnoya/hotelAlura/assets/109557381/5150d174-a412-4c1f-a59a-07f16409eb7c)

E nacionalidades com código e nacionalidade.

![image](https://github.com/jnoya/hotelAlura/assets/109557381/151b783f-c849-4674-bc61-42b56b2b585f)
