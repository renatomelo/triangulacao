# Triangulação

- O código está em Java e os fontes estão no diterório 'src/'. 
- O programa pode ser compilado usando o make. 
- O executável (triangulate) é gerado no diretório 'bin' junto com as suas dependências.

Compilação: `make`
Execução: 	
```cd bin/ 
java triangulate < arquivo_entrada > arquivo_saida
```
A decomposição de um polígono em partes mais simples é muito útil em muitos problemas na Computação. É conhecida como \textit{triangulação} a decomposição de um polígono simples em triângulos. A triangulação é um dos meios mais importantes de particionar um polígono. Um fato da Geometria Computacional muito útil para este trabalho, é que, qualquer triangulação de um polígono simples de $$n$$ vértices sempre contém n-2 triângulos. Vários algoritmos foram desenvolvidos para triangulação, todos caracterizados assintoticamente em função de sua ordem $n$.

Um algoritmo conhecido como \textit{ear clipping} traduzido aqui como "corte de orelha", é o algoritmo descrito neste documento, cujo tempo de execução da melhor versão implementada para este trabalho ficou em ordem de $ O(n^2) $. Existem algoritmos que são melhores assintoticamente em tempo de execução, mas são mais difíceis de implementar. Por exemplo, um algoritmo que faz a decomposição do polígono em trapezoides e depois identifica polígonos monótonos, realiza uma triangulação em tempo $ O(n \log n) $. Além disso, teoricamente, resultados melhores podem ser obtidos, mas há algoritmos para isto que a complexidade é tanta que não se sabe se já foram implementados.
