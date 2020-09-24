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
db.createCollection('Stock');
db.Stock.insert({
    ticker: 'xxxxx',
    open: 'xxxxxx',
    close: 'xxxxx',
    diff: 'xxxxxx'
});