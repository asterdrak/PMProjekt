package rustrll.sl.com.russiantrello;

import android.os.AsyncTask;
import android.util.Log;

import com.julienvey.trello.Trello;
import com.julienvey.trello.domain.Board;
import com.julienvey.trello.domain.Card;
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

    private class ConnectAndFetchToTrello extends AsyncTask {
        @Override
        protected Board doInBackground(Object[] objects) {
            trelloKey = MainActivity.apiKey;
            trelloAccessToken = MainActivity.apiKey;
            trelloApi = new TrelloImpl(trelloKey, trelloAccessToken);
            board = trelloApi.getBoard("HGd6ZbQ7");
            Log.v(TAG, "index=" + board.getName());

            cards = board.fetchMemberCards("5819da32e6f3eeb5060950f6");

//            Card c = cards.get(0);
//            c.setDesc("balba");
//            c.update();

            Log.v(TAG, "index2=" + cards.get(0).getName());


            return board;
        }

        protected void onPostExecute(Board result) {

        }
    }

    public Trellolizer() {
        ConnectAndFetchToTrello connection = new ConnectAndFetchToTrello();
        connection.execute();
    }

    public List<Card> get_cards() {
        return cards;
    }

}
