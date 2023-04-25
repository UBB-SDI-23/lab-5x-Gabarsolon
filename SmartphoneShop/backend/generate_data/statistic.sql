CREATE INDEX tran_cust ON transaction(customer_id)
CREATE INDEX price_idx ON smartphone(price)
CREATE INDEX tran_smart ON transaction(smartphone_id)
CREATE

SELECT customer_id, SUM(price) as total_price
FROM transaction INNER JOIN customer ON transaction.customer_id = customer.id
				 INNER JOIN smartphone ON transaction.smartphone_id = smartphone.id
GROUP BY customer_id
ORDER BY total_price DESC
LIMIT 100