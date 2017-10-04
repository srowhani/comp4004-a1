package server.io.dao;

import org.junit.Test;
import server.io.model.TitleEntity;

import java.util.Optional;
import java.util.stream.Collectors;

import static lib.Assert.assertDoesNotThrow;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TitleTableTests {
    @Test
    public void getInstanceNonNull () {
        assertDoesNotThrow(() -> TitleTable.getInstance());
    }

    @Test
    public void createTitle () {
        TitleTable titleTable = TitleTable.getInstance();
        String isbn = "ISBN";
        String title = "Book title";
        titleTable.createTitle(isbn, title);

        assertTrue(titleTable.getTitleTable().stream().anyMatch(item ->
                item.getISBN().equals(isbn) && item.getBooktitle().equals(title)
        ));

    }

    @Test
    public void deleteTitle () {
        TitleTable titleTable = TitleTable.getInstance();

        titleTable.getTitleTable()
                .stream()
                .map(u -> u.getISBN())
                .collect(Collectors.toList())
                .forEach(id -> titleTable.remove(id));

        assertEquals(titleTable.getTitleTable().size(), 0);
    }

    @Test
    public void lookupRetrievesOptional () {
        fail("Implement this :(");
//        createUser();
//
//        UserTable userTable = UserTable.getInstance();
//        UserEntity userEntity = userTable.getUserTable().get(0);
//
//        Optional<UserEntity> userEntityOptional = userTable.lookup(user -> user.getId() == userEntity.getId());
//        assertTrue(userEntityOptional.isPresent());
//        assertEquals(userEntityOptional.get(), userEntity);
//
//        userEntityOptional = userTable.lookup(user -> user.getUsername() == userEntity.getUsername());
//        assertTrue(userEntityOptional.isPresent());
//        assertEquals(userEntityOptional.get(), userEntity);
    }
}
