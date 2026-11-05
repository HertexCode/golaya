ALTER TABLE reservations
ALTER COLUMN reservation_date TYPE DATE USING reservation_date::DATE;

ALTER TABLE clients
ADD COLUMN url_pictures VARCHAR(500);