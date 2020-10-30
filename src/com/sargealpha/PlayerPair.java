package com.sargealpha;

import org.bukkit.entity.Player;

import java.util.*;

public class PlayerPair {

    private Player p1, p2;
    private boolean isP1Dead, isP2Dead;

    public PlayerPair(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.isP1Dead = false;
        this.isP2Dead = false;
    }

    public Player getP1() {
        return p1;
    }

    public Player getP2() {
        return p2;
    }

    public boolean updateStatus(Player p, boolean status) {
        if (p == null) {
            return false;
        }
        if (p != p1 && p != p2) {
            return false;
        }
        if (p == p1) {
            isP1Dead = status;
        } else {
            isP2Dead = status;
        }
        return true;
    }

    public boolean isPlayerParticipating(Player p) {
        if (p == null) {
            return false;
        }
        return (p == p1 || p == p2) ? true : false;
    }

    public GameCase getCurrentCase() {
        if (isP1Dead && isP2Dead) {
            return GameCase.BOTH_DEAD;
        } else if (isP1Dead && !isP2Dead) {
            return GameCase.P1_DEAD;
        } else if (!isP1Dead && isP2Dead) {
            return GameCase.P2_DEAD;
        } else {
            return GameCase.BOTH_ALIVE;
        }
    }

    public Set<Player> getParticipants() {
        Set<Player> players = new HashSet<>();
        players.add(p1);
        players.add(p2);
        return players;
    }

}
