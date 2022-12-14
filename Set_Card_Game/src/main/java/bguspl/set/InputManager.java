package bguspl.set;

import bguspl.set.ex.Player;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * This class handles the input from the keyboard, translates it to table grid slots and dispatches accordingly.
 */
class InputManager extends KeyAdapter {

    private final static int MAX_KEY_CODE = 255;
    private final Player[] players;
    int[] keyMap = new int[MAX_KEY_CODE + 1];
    int[] keyToSlot = new int[MAX_KEY_CODE + 1];

    public InputManager(Env env, Player[] players) {
        System.out.println("[debug] InputManager");

        this.players = players;

        // initialize the keys
        for (int player = 0; player < env.config.players; ++player)
            for (int i = 0; i < env.config.playerKeys(player).length; i++) {

                int keyCode = env.config.playerKeys(player)[i];
                keyMap[keyCode] = player + 1; // 1 for first player and 2 for second player
                keyToSlot[keyCode] = i;
                System.out.println("[debug] InputManager mapper player :"+player+" keyCode:"+keyCode+" i:"+i);
            }

        for(int i=0;i<keyMap.length;i++){
            System.out.println("[debug] InputManager all keys!! index:"+i+" mapped code:"+keyMap[i]);

        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("[debug] keyPressed:"+e.getKeyCode());

        // dispatch the key event to the player according to the key map
        int keyCode = e.getKeyCode();
        int player = keyMap[keyCode] - 1;
        if (player >= 0)
            players[player].keyPressed(keyToSlot[keyCode]);
    }
}
