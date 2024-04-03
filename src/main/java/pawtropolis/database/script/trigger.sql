CREATE OR REPLACE FUNCTION update_item_location()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.room_id IS NOT NULL AND OLD.bag_id IS NOT NULL THEN
        NEW.bag_id := NULL;
    ELSIF NEW.bag_id IS NOT NULL AND OLD.room_id IS NOT NULL THEN
        NEW.room_id := NULL;
END IF;
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_item_location_trigger
    BEFORE UPDATE ON item
    FOR EACH ROW
    EXECUTE FUNCTION update_item_location();


CREATE OR REPLACE FUNCTION check_item_location()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.room_id IS NULL AND NEW.bag_id IS NULL THEN
        RAISE EXCEPTION 'Gli items devono avere la bag_id o la room_id valorizzati.';
END IF;
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER check_item_location_trigger
    BEFORE INSERT ON item
    FOR EACH ROW
    EXECUTE FUNCTION check_item_location();


CREATE OR REPLACE FUNCTION check_2_item_location()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.room_id IS NOT NULL AND NEW.bag_id IS NOT NULL THEN
        RAISE EXCEPTION 'Gli items non possono avere sia la bag_id che la room_id valorizzati.';
END IF;
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER check_2_item_location_trigger
    BEFORE UPDATE ON item
    FOR EACH ROW
    EXECUTE FUNCTION check_2_item_location();


CREATE OR REPLACE FUNCTION check_species_attributes()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.species_id IN (SELECT id FROM species WHERE id IN (1, 2)) THEN
        IF NEW.tail_length IS NULL THEN
            RAISE EXCEPTION 'Gli animali di questa specie devono avere tail_length non null.';
END IF;
        IF NEW.wingspan IS NOT NULL THEN
            RAISE EXCEPTION 'Gli animali di questa specie non possono avere wingspan valorizzato.';
END IF;
    ELSIF NEW.species_id IN (SELECT id FROM species WHERE id = 3) THEN
        IF NEW.wingspan IS NULL THEN
            RAISE EXCEPTION 'Gli animali di questa specie devono avere wingspan non null.';
END IF;
        IF NEW.tail_length IS NOT NULL THEN
            RAISE EXCEPTION 'Gli animali di questa specie non possono avere tail_length valorizzato.';
END IF;
END IF;
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER check_species_attributes_trigger
    BEFORE INSERT ON animal
    FOR EACH ROW
    EXECUTE FUNCTION check_species_attributes();