CREATE TABLE test (
  _id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT
);

CREATE TRIGGER subject_id_changes
    BEFORE UPDATE OF _id ON test WHEN old._id != new._id
    BEGIN SELECT RAISE(FAIL, 'Not allowed to change column id in subject'); END;