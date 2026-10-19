// Crie um banco chamado store e uma coleção chamada customers.
use store
db.createCollection("customers")

// inserir documentos
db.customers.insertMany([{"name": "Ana", "age": 25, "city": "Salvador", "active": true, "points": 120}])
{
  acknowledged: true,
  insertedIds: { '0': ObjectId('6a987f6208c8c52530d663f7') }
}

db.customers.insertMany([{"name": "Carlos", "age": 28,"city": "Salvador","active": false,"points": 80}])
{
  acknowledged: true,
  insertedIds: { '0': ObjectId('6a987a1c08c8c52530d663f3') }
}

 db.customers.insertMany([{"name": "Bruno",  "age": 32,    "city": "Feira de Santana", "active": true,"points": 300}])
{
  acknowledged: true,
  insertedIds: { '0': ObjectId('6a987a3e08c8c52530d663f4') }
}

db.customers.insertMany([{"name": "Daniela", "age": 40, "city": "São Paulo", "active": true,"points": 500}])
{
  acknowledged: true,
  insertedIds: { '0': ObjectId('6a987a7708c8c52530d663f5') }
}

db.customers.insertMany([{"name": "Eduarda", "age": 22, "city": "Rio de Janeiro", "active": false, "points": 50}])
{
  acknowledged: true,
  insertedIds: { '0': ObjectId('6a987a9908c8c52530d663f6') }
}

// 1  Consulta 

db.customers.find()
[
  {
    _id: ObjectId('6a987a1c08c8c52530d663f3'),
    name: 'Carlos',
    age: 28,
    city: 'Salvador',
    active: false,
    points: 80
  },
  {
    _id: ObjectId('6a987a3e08c8c52530d663f4'),
    name: 'Bruno',
    age: 32,
    city: 'Feira de Santana',
    active: true,
    points: 300
  },
  {
    _id: ObjectId('6a987a7708c8c52530d663f5'),
    name: 'Daniela',
    age: 40,
    city: 'São Paulo',
    active: true,
    points: 500
  },
  {
    _id: ObjectId('6a987a9908c8c52530d663f6'),
    name: 'Eduarda',
    age: 22,
    city: 'Rio de Janeiro',
    active: false,
    points: 50
  }
]

// 2  Atualização

// Antes
{
  "name": "Carlos",
  "active": false
}

// depois
db.customers.updateMany({}, {$set: {"name": "Carlos", "active": true}})
{
  acknowledged: true,
  insertedId: null,
  matchedCount: 4,
  modifiedCount: 4,
  upsertedCount: 0
}

// 3  Atualizar vários documentos

db.customers.updateMany({"city": "Salvador"}, {$set: { "state": "BA"}})


// 4  Incremento

// Antes
{
  "name": "Ana",
  "points": 120
}

// depois
db.customers.updateOne({"name": "Ana"}, {$set: {"points" : 170}})
 {
    _id: ObjectId('6a987f6208c8c52530d663f7'),
    name: 'Ana',
    age: 25,
    city: 'Salvador',
    active: true,
    points: 170
  }

// 5 Inserção

db.customers.insertOne({"name": "Fernando","age": 29,"city": "Recife","active": true,"points": 90})

//  6  Remoção

db.customers.deleteOne({ "name": "Eduarda" })

// 7  Criar um novo campo

db.customers.updateOne({ "name": "Daniela" },{ $set: { "vip": true } })

// 8  Remover um campo

db.customers.updateOne({ "name": "Bruno" },{ $unset: { "points": "" } })

// 9  Ordenação

db.customers.find().sort({ "age": -1 })

// 10  Filtro com múltiplas condições

db.customers.find({ "active": true, "age": { $gt: 30 } },{ "name": 1, "_id": 0 })

// Desafios

// 1. Mostrar apenas os nomes dos clientes
db.customers.find({}, { name: 1, _id: 0 })

// 2. Contar quantos clientes existem
db.customers.countDocuments()

// 3. Contar apenas os clientes ativos
db.customers.countDocuments({ active: true })

// 4. Mostrar o cliente com maior pontuação
db.customers.find().sort({ points: -1 }).limit(1)

// 5. Mostrar o cliente com menor idade
db.customers.find().sort({ age: 1 }).limit(1)

// 6. Mostrar apenas clientes com pontuação entre 100 e 400

// 7. Mostrar apenas clientes das cidades de Salvador ou São Paulo
db.customers.find({ city: { $in: ["Salvador", "São Paulo"] } })

// 8. Mostrar todos os clientes ordenados por nome
db.customers.find().sort({ name: 1 })

// 9. Mostrar apenas os três primeiros clientes
db.customers.find().limit(3)

// 10. Mostrar apenas os clientes inativos
db.customers.find({ active: false })
