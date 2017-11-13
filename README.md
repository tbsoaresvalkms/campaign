# API

Para verificar os resources da API, olhe aqui: [Index](doc/index.md)

##Requisitos
* Java 8+
* Lombok (necessário plugin em IDEs)
* Maven
* port 8080  (urls dos serviços estão mapeadas nesta port)

##Componentes da Aplicação
 
* Spring Boot
* Spring Rest
* Spring ActiveMQ
* Spring Data
* Database h2
* Mockito
* JUnit
* Lombok
* Maven
 
## Commentarios

* Foi utilizado a arquitetura MVC com commands.

* Cada funcionalidade, está separada em uma única classe,
   mantendo o princípio da responsabilidade única.
   
* Sempre que possível, utilizado stream e optional, para
   evitar if's, try/catch e for/while.
   
* A regra de não cadastrar na mesma vigência foi isolada na classe
   campaign.services.AdjustEndPeriod, utilizando o conceito de recursão
   foi reaproveitada tanto na criação quanto na atualização das campanhas.
   
* Adicionado um atributo updateAt no model Campaign, onde é atualizado pelo
    spring data, quando salva a entidade no banco, assim permite outros sistemas
    saberem quando as campanhas foram atualizadas.
   
* A associação do cliente com a campanha é feita pelo e-mail do cliente
   e id da campanha.
   
* Foi utilizado ResponseEntityExceptionHandler para controlar, personalizar e
    expandir as exceptions.
   
* Na criação do customer, foi utilizado ActiveMQ para fazer os registros
   nas campanhas de forma independentes, sendo assim, se a api estiver fora
   não impactará a criação do customer, além disso, o ActiveMQ fará novas tentativas.

* Qualquer problema com a integração das API, será lançado uma CampaignClientException,
    onde responderá uma mensagem amigável ao cliente.









# Stream - firstChar
   - Utilizado o conceito de autômato finito:
       - Estado inicial: q0
       - Estado final: q3
       - C representa consoante
       - V representa vogal
      
   - Conforme a leitura de cada posição da Stream, o 'ponteiro' se move de acordo com o autômato abaixo.
   - A cada leitura, o char lido é guardado em um array para verificar a repetição
   - Quando o autômato chega no estado final (Q3), significa que a regra foi atendida, (vogal, consoante, vogal)
   - As vogais candidatas (chegaram no Q3), säo armazenadas em um array, pois existe a possibilidade de serem repetidas
   - Criado um teste com stream de 100.000.000 char, para verificar performance, tempo de execução 1,5 s
  
  
  ![automato](https://raw.githubusercontent.com/tbsoaresvalkms/campaign/master/automato.jpeg)
      
     


# Deadlock !
   - Deadlock ocorre quando o processo A fica aguardando a disponibilidade de
       um recurso utilizado pelo processo B, ao mesmo tempo, o processo B
       aguarda o recurso utilizado pelo processo A. Assim, ambos processos
       ficaram aguardando, sem prosseguir com a execução.

   - Um programa Java pode sofrer um deadlock porque a palavra-chave synchronized
       faz com que a execução seja bloqueada enquanto aguarda o outro objeto.
       Nesse caso, se o outro objeto estiver aguardando o primeiro, então teremos
       uma situação de deadlock.

   - Uma maneira de evitar deadlocks é evitar que os processos sejam cruzados desta maneira.
  
   - Dependendo do contexto, a reordenação das chamadas dos processos pode evitar deadlock.
  
  
# ParallelStreams !
   - ParallelStreams divide a execução da tarefa em várias threads, aproveitando
       todos os núcleos disponíveis da CPU, logo a execução é mais rápida comparada
       com stream sequencial, onde a execução é em apenas em uma thread de forma sequencial,
       idêntica ao for-loop.
      
   - Na utilização do ParallelStreams não pode prever a ordem de execução de cada
       elemento. Isso ocorre porque a execução é dividida em duas ou mais threads,
       a ordem na qual o processador executa as threads não é previsível. Se a execução
       depende da ordem dos elementos, não se deve usar ParallelStreams.
      
   - Outro ponto importante, em aplicações que utilizam várias threads, deve utilizar
       ParallelStreams com cautela, pois serão mais threads para concorrer no uso dos
       núcleos da CPU. Um exemplo em aplicações WEB, onde existem várias threads abertas,
       utilizar ParallelStreams, pode impactar no desempenho das requisições.
