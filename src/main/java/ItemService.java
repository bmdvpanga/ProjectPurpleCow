import java.util.*;

public class ItemService {

        private HashMap<String, Item> items = new HashMap<>();

        public List<Item> getAllItems() {
            return new ArrayList<>(items.values());
        }


        public Item getItemById(String id) {
            if (items.containsKey(id)) {
                return items.get(id);
            }

            return null;
        }

        public void deleteItemById(String id) {
            if (items.containsKey(id)) {
                items.remove(id);
            }
        }

        public void createItem(String value) {
                Item item = new Item(value);
                items.put(item.getId(), item);

        }

        public void updateItem(String id, String value) {
                Item item = items.get(id);
                item.setValue(value);
        }

        public void deleteAll() {
            items.clear();
        }




}
