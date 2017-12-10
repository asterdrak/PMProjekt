package rustrll.sl.com.russiantrello;

import android.os.AsyncTask;
import android.util.Log;

import com.julienvey.trello.Trello;
import com.julienvey.trello.domain.Board;
import com.julienvey.trello.domain.Card;
import com.julienvey.trello.domain.Label;
import com.julienvey.trello.domain.TList;
import com.julienvey.trello.impl.TrelloImpl;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by asterdrak on 04.12.17.
 */

public class Trellolizer {
    private String trelloKey;
    private String trelloAccessToken;
    private Trello trelloApi;
    private Board board;
    private List<Card> cards;
    private DatabaseHelper database;

    private class ConnectAndFetchToTrello extends AsyncTask {
        @Override
        protected Board doInBackground(Object[] objects) {
            trelloKey = "1a6f32c4b9a93d5b1b3fb4293ef8de9e";
            trelloAccessToken = "0339ee126b22caf401edd740b3d478cdbb8299830af3bb3db24ab8318858f3c0";
            trelloApi = new TrelloImpl(trelloKey, trelloAccessToken);
            board = trelloApi.getBoard("HGd6ZbQ7");
            Log.v(TAG, "index=" + board.getName());

            cards = board.fetchMemberCards("5819da32e6f3eeb5060950f6");

            database = MainActivity.myDb;

//            Card c = cards.get(0);
//            c.setDesc("balba");
//            c.update();

            Log.v(TAG, "index2=" + cards.get(0).getName());
            onPostExecute(board);

            return board;
        }

        protected void onPostExecute(Board result) {
            database.clearAllData();
            String name, list, due_date, moscow = "", tmp;
            List<Label> labels;
            List<Card> cards = get_cards();
            for(int i = 0; i < cards.size(); i++) {
                name = cards.get(i).getName();
                list = cards.get(i).getIdList();
                if(cards.get(i).getDue() == null) {
                    due_date = "";
                } else {
                    due_date = cards.get(i).getDue().toString();
                }
                labels = cards.get(i).getLabels();
                for(int ii = 0; ii < labels.size(); ii++) {
                    moscow = "";
                    if (labels.get(ii).getName().length() > 7) {
                        tmp = labels.get(ii).getName().substring(0, 7);
                        if (tmp.equals("russian")) {
                            moscow = labels.get(ii).getName();
                            break;
                        }
                    }
                }
                database.insertData(name, list, due_date, moscow);
            }
        }
    }

    public Trellolizer() {
        ConnectAndFetchToTrello connection = new ConnectAndFetchToTrello();
        connection.execute();
//        connection.onPostExecute(board);
    }

    public List<Card> get_cards() {
        return cards;
    }

}
