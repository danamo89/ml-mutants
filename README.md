# ml-mutants

Este ejercicio esta hosteado en servidores de Heroku.

# API Test
Para consultar la API, se debe hacer una peticion POST a la URL: https://gentle-headland-97642.herokuapp.com/mutant indicando en el body la cadena de ADN a evaluar.

Ej:

{
  "dna":["ATGCG","CAGTG","TTATG","AGAAG","CCCCT","TCACT","AGCTT","GATAC"]
}

Si es mutante, la respuesta es un HTTP 200-OK.
Si es humano, la respuesta es un HTTP 403-FORBIDEN.

# Nota!!
El primer request puede tardar más de lo normal, ya que los Dynos de los servidores pasan a un estado "Sleep" luego de 30 minutos de inactividad.
https://devcenter.heroku.com/articles/getting-started-with-java#scale-the-app

