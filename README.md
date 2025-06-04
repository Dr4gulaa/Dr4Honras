# Dr4Honras
Plugin de sistema de honras feito para o servidor Altverse Network para o minecraft na versão 1.7.10, o sistema de honras é um rankeamento que o player pode ir crescendo e obtendo mais vantagens dentro do servidor.

Rankeamento:

Rank SS
Rank S
Rank A
Rank B
Rank C
Rank D
Rank E

## Funcionalidades

Sistema de Scheduler, onde a todos os players que ficarem online por 10 minutos, ganham 1 pontos de honra.

Sitema de permissão por rank.

Sistema de honras2x por tempo determinado.

https://github.com/Dr4gulaa/Dr4Bounty/assets/77586106/74530caf-33ef-4a6f-98a8-c023421df315


## 💼 Comandos

Players:

  /honras:
    description: Exibe quantidade expecifica de honras do player.
    usage: /honras
    permission: dr4honras.use

Admins:

  sethonra:
    description: Adiciona uma quantidade especifica de honra para o player.
    usage: /sethonra <player> <amount>
    permission: dr4honras.manager

  delhonra:
    description: Retira uma quantidade especifica de honra para o player.
    usage: /delhonra <player> <amount>
    permission: dr4honras.manager

  addhonra:
    description: Adiciona uma quantia de honra no valor que o player já tem.
    usage: /addhonra <player> <amount>
    permission: dr4honras.manager

  honras2x:
    description: Ativa multiplicador de honras por tempo determinado.
    usage: /honras2x <tempo>
    permission: dr4honras.manager
