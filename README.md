# api_desafio_pitang


<h1 align="left"> ESTÓRIAS DE USUÁRIO </h1>

<b>HU001 - Funcionalidade: Cadastro Usuário</b>

Como um atendente do Detran-PE <br>
Eu quero poder cadastrar, atualizar,deletar e consultar usuários<br>
Para utilizar essas informações em outras partes do sistema.<br>

Cenário 1: Buscar todos os usuários<br>
Dado que vou precisar da lista de todos os usuários<br>
E não precisarei me autenticar, nem enviar nenhum campo na requisição.<br>
Então obterei os dados firstName, lastName, email, birthday, login, password, phone, cars(yer, licensePlate, model, color).<br>

Cenário 2: Buscar um usuário pelo id<br>
Dado que vou precisar obter os dados de um usuário específico<br>
E não precisarei me autenticar e enviar na requisição o campo ID referente ao usuário.<br>
Então obterei os dados firstName, lastName, email, birthday, login, password, phone, cars(yer, licensePlate, model, color).<br>

Cenário 3: Cadastrar um novo usuário<br>
Dado que preciso cadastrar um novo usuário<br>
E não precisarei me autenticar,<br>
Então devo enviar uma requisição JSON contendo todos os dados do usuário (firstName, lastName, email, birthday, login, password, phone, "cars") e dentro de cars, uma lista contendo todos os dados dos carros (yer, licensePlate, model, color).<br>
Se algum dado já existir ou for inválido, retornar o erro informando qual o campo.<br>

Cenário 4: Deletar um usuário pelo id<br>
Dado que vou precisar deletar os dados de um usuário específico<br>
E a mesma não necessita de autenticação,<br>
Então enviarei o campo ID referente ao usuário.<br>

Cenário 5: Atualizar um usuário<br>
Dado que preciso atualizar um novo usuário<br>
E não precisarei me autenticar, <br>
Então devo enviar uma requisição JSON contendo todos os dados do usuário (firstName, lastName, email, birthday, login, password, phone, "cars") e dentro de cars, uma lista contendo todos os dados dos carros (yer, licensePlate, model, color).<br>
Se algum dado já existir ou for inválido, retornar o erro informando qual o campo.<br>

Cenário 6: Buscar informações usuário logado
Dado que vou precisar obter os dados do usuário logado
E precisarei estar autenticado
E passando o token na requisição
Então obterei os dados firstName, lastName, email, birthday, login, password, phone, cars(yer, licensePlate, model, color) + createdAt (data da criação do usuário) + lastLogin (data da última vez
que o usuário realizou login).<br>

<b>HU002 - Funcionalidade: Cadastro Carros</b>

Como um atendente do Detran-PE<br>
Eu quero poder cadastrar, atualizar,deletar e consultar carros, associando-os a usuários<br>
Para utilizar essas informações em outras partes do sistema.<br>

Cenário 1: Buscar todos os carros<br>
Dado que vou precisar da lista de todos os carros<br>
Então precisarei me autenticar e gerar a requisição com o token no header.<br>

Cenário 2: Buscar um carro pelo id<br>
Dado que vou precisar obter os dados de um carro específico<br>
Então precisarei me autenticar e gerar a requisição com o token no header e o campo id.<br>

Cenário 3: Cadastrar um novo carro<br>
Dado que preciso cadastrar um novo carro<br>
Então precisarei me autenticar, enviar uma requisição JSON contendo todos os dados do carro (yer, licensePlate, model, color) e enviar o token no header.<br>
Se algum dado já existir ou for inválido, retornar o erro informando qual o campo.<br>

Cenário 4: Deletar um carro pelo id<br>
Dado que vou precisar deletar os dados de um carro específico<br>
Então precisarei me autenticar, enviarei o campo ID referente ao carro e o token no header.<br>

Cenário 5: Atualizar um carro<br>
Dado que preciso atualizar um novo carro<br>
Então precisarei me autenticar, enviar uma requisição JSON contendo todos os dados do carro (yer, licensePlate, model, color) e enviar o token no header.<br>
Se algum dado já existir ou for inválido, retornar o erro informando qual o campo.<br>

<b>HU003 - Funcionalidade: Autenticação</b>

Como um gestor de TI<br>
Eu quero garantir a segurança do meu sistema utilizando token(JWT) com as informações do usuário<br>
Para evitar vazamentos de dados sensíveis.<br>

Cenário 1: Ao utilizar rotas que exigem autenticação com token<br>
Dado que vou precisar enviar o token em algumas rotas e estou enviando o token<br>
Então preciso que seja validado e caso não esteja válido, retornar um erro "Unauthorized - invalid session"
E não traga nenhum dado.<br>

Cenário 2: Ao utilizar rotas que exigem autenticação sem token.<br>
Dado que vou precisar enviar o token em algumas rotas e não estou enviando o token<br>
Então preciso que seja validado e caso não esteja válido, retornar um erro "Unauthorized"
E não traga nenhum dado.<br>

Cenário 3: Logar<br>
Dado que vou precisar obter o token para enviar em algumas rotas<br>
Então preciso que seja validado os campos "login" e "password". Caso não esteja válido, retornar um erro "Invalid login or password"<br>
E não traga nenhum dado.