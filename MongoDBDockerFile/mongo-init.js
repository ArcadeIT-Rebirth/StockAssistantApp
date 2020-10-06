db.createUser(
    {
        user: "stock-assistant",
        pwd: "$t0Ck4S$!sT@Nt",
        roles: [
            {
                role: "readWrite",
                db: "StockHistory"
            }
        ]
    }
);


db.auth("stock-assistant", "$t0Ck4S$!sT@Nt");
db.createCollection('ticker');
db.getCollection('ticker').insert({
    name: 'xxxxx',
    data: [
        {
            date: new Date(2020, 1, 1),
            open: 0.0,
            high: 0.0,
            low: 0.0,
            close: 0.0,
            vol: 0
        },
        {
            date: new Date(2020, 1, 2),
            open: 0.0,
            high: 0.0,
            low: 0.0,
            close: 0.0,
            vol: 0
        }
    ]
});