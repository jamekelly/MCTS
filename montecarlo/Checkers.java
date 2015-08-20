/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package montecarlo;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Simulates the rules for a game of 6x6 checkers
 * @author James
 */
public class Checkers {
    
    private int[][] state;
    
    public Checkers() { 
        this.state = new int[][]
        {
            {1,0,1,0,1,0,1,0},
            {0,1,0,1,0,1,0,1},
            {1,0,1,0,1,0,1,0},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0},
            {0,2,0,2,0,2,0,2},
            {2,0,2,0,2,0,2,0},
            {0,2,0,2,0,2,0,2}
        };
    }
    
    public Checkers(int[][] state) {
        this.state = state;
    }
    
    public boolean isOver() {
        if(!contains(1) || !contains(2)){
            return true;
        }
        else {
            if((!canOpponentManMove() && !canOpponentScore() && !canOppKingMove() && !canOppKingScore())
                    || (!canIMove() && !canIScore() && !canMyKingMove() && !canMyKingScore())){
                return true;
            }
        }
        return false;
    }
    
    public boolean contains(int player) {
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[i].length; j++) {
                if (state[i][j] == player || state[i][j] == player + 2) {
                    return true;
                }
            } 
        }
        return false;
    }
    
    public boolean canOpponentManMove() {
        for(int i = 0;i<state.length - 1;i++){
            for(int j = 0;j<state[i].length;j++){
                if(this.canOpponentManMove(i, j))
                    return true;
            }
        }
        return false;
    }
    
    public boolean canOpponentManMove(String dir, int i, int j) {
        if(state[i][j] == 1 || state[i][j] == 3){
            if(i<state.length - 1){
                if(dir.equalsIgnoreCase("left")){
                    if(j > 0){
                        if(state[i+1][j-1] == 0){
                            return true;
                        }
                    }
                }
                else {
                    if(j < state.length - 1){
                        if(state[i+1][j+1] == 0){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public boolean canMyKingMove() {
        for(int i = 0;i<state.length - 1;i++){
            for(int j = 0;j<state[i].length;j++){
                if(this.canMyKingMove(i, j))
                    return true;
            }
        }
        return false;
    }
    
    public boolean canMyKingMove(String dir, int i, int j){
        if(state[i][j] == 4){
            if(i<state.length - 1){
                if(dir.equalsIgnoreCase("left")){
                    if(j > 0) {
                        if(state[i+1][j-1] == 0){
                            return true;
                        }
                    }
                }
                else {
                    if(j < state.length - 1){
                        if(state[i+1][j+1] == 0){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public boolean canOpponentManMove(int i, int j){
                return(canOpponentManMove("left", i, j) || canOpponentManMove("right", i, j));
    }
    
    public boolean canMyKingMove(int i, int j) {
        return (canMyKingMove("left", i, j) || canMyKingMove("right", i, j));
    }
    
    public boolean canOpponentScore() {
        for(int i = 0;i<state.length - 2;i++){
            for(int j = 0;j<state[i].length;j++){
                if(canOpponentScore(i,j))
                    return true;
                }
            }
        return false;
    }
    
    public boolean canOpponentScore(String dir, int i, int j) {
        if(state[i][j] == 1 || state[i][j] == 3){
            if(i<state.length - 2){
                if(dir.equalsIgnoreCase("left")){
                    if(j > 1){
                        if((state[i+1][j-1] == 2 || state[i+1][j-1] == 4) && state[i+2][j-2] == 0){
                            return true;
                        }
                    }
                }
                else {
                    if(j < state.length - 2){
                        if((state[i+1][j+1] == 2 || state[i+1][j+1] == 4) && state[i+2][j+2] == 0){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public boolean canOpponentScore(int[][] nState, String dir, int i, int j) {
        if(nState[i][j] == 1 || nState[i][j] == 3){
            if(i<nState.length - 2){
                if(dir.equalsIgnoreCase("left")){
                    if(j > 1){
                        if((nState[i+1][j-1] == 2 || nState[i+1][j-1] == 4) && nState[i+2][j-2] == 0){
                            return true;
                        }
                    }
                }
                else {
                    if(j < nState.length - 2){
                        if((nState[i+1][j+1] == 2 || nState[i+1][j+1] == 4) && nState[i+2][j+2] == 0){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public boolean canMyKingScore() {
        for(int i = 0;i<state.length - 2;i++){
            for(int j = 0;j<state[i].length;j++){
                if(this.canMyKingScore(i,j))
                    return true;
                }
            }
        return false;
    }
    
    public boolean canMyKingScore(String dir, int i, int j){
        if(state[i][j] == 4){
            if(i<state.length - 2){
                if(dir.equalsIgnoreCase("left")){
                    if(j > 1){
                        if((state[i+1][j-1] == 1 || state[i+1][j-1] == 3) && state[i+2][j-2] == 0){
                            return true;
                        }
                    }
                }
                else {
                    if(j < state.length - 2){
                        if((state[i+1][j+1] == 1 || state[i+1][j+1] == 3) && state[i+2][j+2] == 0){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public boolean canMyKingScore(int[][] nState, String dir, int i, int j){
        if(nState[i][j] == 4){
            if(i<nState.length - 2){
                if(dir.equalsIgnoreCase("left")){
                    if(j > 1){
                        if((nState[i+1][j-1] == 1 || nState[i+1][j-1] == 3) && nState[i+2][j-2] == 0){
                            return true;
                        }
                    }
                }
                else {
                    if(j < nState.length - 2){
                        if((nState[i+1][j+1] == 1 || nState[i+1][j+1] == 3) && nState[i+2][j+2] == 0){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public boolean canOpponentScore(int i, int j) {
        return (canOpponentScore("left", i, j) || canOpponentScore("right", i, j));
    }
    
    public boolean canMyKingScore(int i, int j){
        return (this.canMyKingScore("left", i, j) || this.canMyKingScore("right", i, j));
    }
    
    public boolean canIMove(){
        for(int i = 1;i<state.length;i++){
            for(int j = 0; j< state[i].length;j++){
                if(canIMove(i,j))
                    return true;
            }
        }
        return false;
    }
    
    public boolean canIMove(String dir, int i, int j) {
        if(state[i][j] == 2 || state[i][j] == 4){
            if(i>0){
                if(dir.equalsIgnoreCase("left")){
                    if(j > 0){
                        if(state[i-1][j-1] == 0){
                            return true;
                        }
                    }
                }
                else {
                    if(j < state.length - 1){
                        if(state[i-1][j+1] == 0){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public boolean canOppKingMove() {
        for(int i = 1;i<state.length;i++){
            for(int j = 0; j< state[i].length;j++){
                if(canOppKingMove(i,j))
                    return true;
            }
        }
        return false;
    }
    
    public boolean canOppKingMove(String dir, int i, int j){
        if(state[i][j] == 3){
            if(i>0){
                if(dir.equalsIgnoreCase("left")){
                    if(j > 0){
                        if(state[i-1][j-1] == 0){
                            return true;
                        }
                    }
                }
                else {
                    if(j < state.length - 1){
                        if(state[i-1][j+1] == 0){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public boolean canIMove(int i, int j) {
        return (canIMove("left", i, j) || canIMove("right", i, j));
    }
    
    public boolean canOppKingMove(int i, int j){
        return (canOppKingMove("left", i, j) || canOppKingMove("right", i, j));
    }
    
    public boolean canIScore() {
        for(int i = 2;i<state.length;i++){
            for(int j = 0;j<state[i].length;j++) {
                if(canIScore(i,j))
                    return true;
                }
            }
        return false;
    }
    
    public boolean canIScore(String dir, int i, int j) {
        if(state[i][j] == 2 || state[i][j] == 4){
            if(i>1){
                if(dir.equalsIgnoreCase("left")){
                    if(j > 1){
                        if((state[i-1][j-1] == 1 || state[i-1][j-1] == 3) && state[i-2][j-2] == 0){
                            return true;
                        }
                    }
                }
                else {
                    if(j < state.length - 2){
                        if((state[i-1][j+1] == 1 || state[i-1][j+1] == 3) && state[i-2][j+2] == 0){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public boolean canIScore(int[][] nState, String dir, int i, int j) {
        if(nState[i][j] == 2 || nState[i][j] == 4){
            if(i>1){
                if(dir.equalsIgnoreCase("left")){
                    if(j > 1){
                        if((nState[i-1][j-1] == 1 || nState[i-1][j-1] == 3) && nState[i-2][j-2] == 0){
                            return true;
                        }
                    }
                }
                else {
                    if(j < nState.length - 2){
                        if((nState[i-1][j+1] == 1 || nState[i-1][j+1] == 3) && nState[i-2][j+2] == 0){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public boolean canOppKingScore(){
        for(int i = 2;i<state.length;i++){
            for(int j = 0;j<state[i].length;j++) {
                if(canOppKingScore(i,j))
                    return true;
                }
            }
        return false;
    }
    
    public boolean canOppKingScore(String dir, int i, int j){
        if(state[i][j] == 3){
            if(i>1){
                if(dir.equalsIgnoreCase("left")){
                    if(j > 1){
                        if((state[i-1][j-1] == 2 || state[i-1][j-1] == 4) && state[i-2][j-2] == 0){
                            return true;
                        }
                    }
                }
                else {
                    if(j < state.length - 2){
                        if((state[i-1][j+1] == 2 || state[i-1][j+1] == 4) && state[i-2][j+2] == 0){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public boolean canOppKingScore(int[][] nState, String dir, int i, int j){
        if(nState[i][j] == 3){
            if(i>1){
                if(dir.equalsIgnoreCase("left")){
                    if(j > 1){
                        if((nState[i-1][j-1] == 2 || nState[i-1][j-1] == 4) && nState[i-2][j-2] == 0){
                            return true;
                        }
                    }
                }
                else {
                    if(j < nState.length - 2){
                        if((nState[i-1][j+1] == 2 || nState[i-1][j+1] == 4) && nState[i-2][j+2] == 0){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public boolean canIScore(int i, int j) {
            return (canIScore("left", i, j) || canIScore("right", i, j));
    }
    
    public boolean canOppKingScore(int i, int j){
        return (canOppKingScore("left", i, j) || canOppKingScore("right", i, j));
    }
    
    public int getValue(){
        if(!contains(1) || (!canOpponentManMove() && !canOpponentScore()
                && !canOppKingMove() && !canOppKingScore())){
            return 1;
        } else if(!contains(2) || (!canIMove() && !canIScore()
                && !canMyKingMove() && !canMyKingScore())){
            return -1;
        } else {
            return 0;
        }
    }

    public int[][] getState() {
        return state;
    }
    
    public void king(int[][] st) {
        for(int i = 0;i<st.length;i++){
            if(st[state.length - 1][i] == 1)
                st[state.length - 1][i] = 3;
            if(st[0][i] == 2)
                st[0][i] = 4;
        }
    }
    
    public void king() {
        for(int i = 0;i<state.length;i++){
            if(state[state.length - 1][i] == 1)
                state[state.length - 1][i] = 3;
            if(state[0][i] == 2)
                state[0][i] = 4;
        }
    }
    
    public int[][] oppMoveMyRight(int i, int j, ArrayList<int[][]> moves) throws Exception {
        if(moves == null)
            moves = new ArrayList<>();
        int[][] nState = new int[state.length][];
        for(int l = 0;l<state.length;l++){
            int[] row = state[l];
            int len = row.length;
            nState[l] = new int[len];
            System.arraycopy(row, 0, nState[l], 0, len);
        }
        if(i == state.length - 2){
            if((state[i][j] == 1 || state[i][j] == 3) && state[i+1][j+1] == 0){
                nState[i+1][j+1] = state[i][j];
                nState[i][j] = 0;
            }
            else {throw new Exception();}
        }else if(i == state.length - 1){
            if(state[i][j] == 3){
                nState = null;
                nState = this.oppKingMyRight(i, j, moves);//, nState);
            }
            else {throw new Exception();}
        }
        else{
            if((state[i][j] == 1 || state[i][j] == 3) && 
                    (state[i+1][j+1] == 2 || state[i+1][j+1] == 4) && state[i+2][j+2] == 0){
                nState[i+2][j+2] = state[i][j];
                nState[i+1][j+1] = 0;
                nState[i][j] = 0;
                if(this.canOpponentScore(nState, "left", i+2, j+2)){
                    Checkers t = new Checkers(nState);
                    return t.oppMoveMyLeft(i+2, j+2, moves);
                } else if(this.canOpponentScore(nState, "right", i+2, j+2)){
                    Checkers t = new Checkers(nState);
                    return t.oppMoveMyRight(i+2, j+2, moves);
                }
            } else if((state[i][j] == 1 || state[i][j] == 3) && state[i+1][j+1] == 0){
                nState[i+1][j+1] = state[i][j];
                nState[i][j] = 0;
            }
            else if(state[i][j] == 3){
                nState = null;
                nState = this.oppKingMyRight(i, j, moves);//, nState);
            }
            else {throw new Exception();}
        }
        for(int k = 0; k < moves.size(); k++){
            if(Arrays.deepEquals(nState, moves.get(k))){
                throw new Exception();
            }
        }
        return nState;
    }
    
    public int[][] oppMoveMyLeft(int i,int j, ArrayList<int[][]> moves) throws Exception {
        if(moves == null)
            moves = new ArrayList<>();
        int[][] nState = new int[state.length][];
        for(int l = 0;l<state.length;l++){
            int[] row = state[l];
            int len = row.length;
            nState[l] = new int[len];
            System.arraycopy(row, 0, nState[l], 0, len);
        }
        if(i == state.length - 2){
            if((state[i][j] == 1 || state[i][j] == 3) && state[i+1][j-1] == 0){
                nState[i+1][j-1] = state[i][j];
                nState[i][j] = 0;
            }
            else {throw new Exception();}
        }else if(i == state.length - 1){
            if(state[i][j] == 3){
                nState = null;
                nState = this.oppKingMyLeft(i, j, moves);//, nState);
            }
            else {throw new Exception();}
        }
        else{
            if((state[i][j] == 1 || state[i][j] == 3) 
                    && (state[i+1][j-1] == 2 || state[i+1][j-1] == 4) && state[i+2][j-2] == 0){
                nState[i][j] = 0;
                nState[i+1][j-1] = 0;
                nState[i+2][j-2] = state[i][j];
                if(this.canOpponentScore(nState, "left", i+2, j-2)){
                    Checkers t = new Checkers(nState);
                    return t.oppMoveMyLeft(i+2, j-2, moves);
                } else if(this.canOpponentScore(nState, "right", i+2, j-2)){
                    Checkers t = new Checkers(nState);
                    return t.oppMoveMyRight(i+2, j-2, moves);
                }
            }
            else if((state[i][j] == 1 || state[i][j] == 3) && state[i+1][j-1] == 0){
                nState[i+1][j-1] = state[i][j];
                nState[i][j] = 0;
            }
            else if(state[i][j] == 3){
                nState = null;
                nState = this.oppKingMyLeft(i, j, moves);//, nState);
            }
            else {throw new Exception();}
        }
        for(int k = 0; k < moves.size(); k++){
            if(Arrays.deepEquals(nState, moves.get(k))){
                throw new Exception();
            }
        }
        return nState;
    }
    
    public int[][] oppKingMyRight(int i, int j, ArrayList<int[][]> moves)/*, int[][] nState)*/ throws Exception {
        if(moves == null)
            moves = new ArrayList<>();
        int[][] nState = new int[state.length][];
        for(int l = 0;l<state.length;l++){
            int[] row = state[l];
            int len = row.length;
            nState[l] = new int[len];
            System.arraycopy(row, 0, nState[l], 0, len);
        }
        if(state[i][j] == 3 && (state[i-1][j+1] == 2 || state[i-1][j+1] == 4) && state[i-2][j+2] == 0){
            nState[i][j] = 0;
            nState[i-1][j+1] = 0;
            nState[i-2][j+2] = state[i][j];
            if(this.canOppKingScore(nState, "left", i-2, j+2)){
                Checkers t = new Checkers(nState);
                return t.oppKingMyLeft(i-2, j+2, moves);
            } else if(this.canOppKingScore(nState, "right", i-2, j+2)){
                Checkers t = new Checkers(nState);
                return t.oppKingMyRight(i-2, j+2, moves);
            }
        }
        else if(state[i][j] == 3 && state[i-1][j+1] == 0){
            nState[i][j] = 0;
            nState[i-1][j+1] = 3;
        }
        else {throw new Exception();}
        for(int k = 0; k < moves.size(); k++){
            if(Arrays.deepEquals(nState, moves.get(k))){
                throw new Exception();
            }
        }
        return nState;
    }
    
    public int[][] oppKingMyLeft(int i, int j, ArrayList<int[][]> moves)/*, int[][] nState)*/ throws Exception {
        if(moves == null)
            moves = new ArrayList<>();
        int[][] nState = new int[state.length][];
        for(int l = 0;l<state.length;l++){
            int[] row = state[l];
            int len = row.length;
            nState[l] = new int[len];
            System.arraycopy(row, 0, nState[l], 0, len);
        }
        if(state[i][j] == 3 && (state[i-1][j-1] == 2 || state[i-1][j-1] == 4) && state[i-2][j-2] == 0){
            nState[i][j] = 0;
            nState[i-1][j-1] = 0;
            nState[i-2][j-2] = 3;
            if(this.canOppKingScore(nState, "left", i-2, j-2)){
                Checkers t = new Checkers(nState);
                return t.oppKingMyLeft(i-2, j-2, moves);
            } else if(this.canOppKingScore(nState, "right", i-2, j-2)){
                Checkers t = new Checkers(nState);
                return t.oppKingMyRight(i-2, j-2, moves);
            }
        }
        else if(state[i][j] == 3 && state[i-1][j-1] == 0){
            nState[i][j] = 0;
            nState[i-1][j-1] = 3;
        }
        else {throw new Exception();}
        for(int k = 0; k < moves.size(); k++){
            if(Arrays.deepEquals(nState, moves.get(k))){
                throw new Exception();
            }
        }
        return nState;
    }
    
    public int[][] myMoveMyRight(int i, int j, ArrayList<int[][]> moves) throws Exception {
        if(moves == null)
            moves = new ArrayList<>();
        int[][] nState = new int[state.length][];
        for(int l = 0;l<state.length;l++){
            int[] row = state[l];
            int len = row.length;
            nState[l] = new int[len];
            System.arraycopy(row, 0, nState[l], 0, len);
        }
        if((state[i][j] == 2 || state[i][j] == 4)&& (state[i-1][j+1] == 1 || state[i-1][j+1] == 3) && state[i-2][j+2] == 0){
            nState[i][j] = 0;
            nState[i-1][j+1] = 0;
            nState[i-2][j+2] = state[i][j];
            if(this.canIScore(nState, "left", i-2, j+2)){
                Checkers t = new Checkers(nState);
                return t.myMoveMyLeft(i-2, j+2, moves);
            } else if(this.canIScore(nState, "right", i-2, j+2)){
                Checkers t = new Checkers(nState);
                return t.myMoveMyRight(i-2, j+2, moves);
            }
        } else if((state[i][j] == 2 || state[i][j] == 4) && state[i-1][j+1] == 0){
            nState[i][j] = 0;
            nState[i-1][j+1] = state[i][j];
        }
        else {throw new Exception();}
        for(int k = 0; k < moves.size(); k++){
            if(Arrays.deepEquals(nState, moves.get(k))){
                throw new Exception();
            }
        }
        return nState;
    }
    
    public int[][] myMoveMyLeft(int i, int j, ArrayList<int[][]> moves) throws Exception {
        if(moves == null)
            moves = new ArrayList<>();
        int[][] nState = new int[state.length][];
        for(int l = 0;l<state.length;l++){
            int[] row = state[l];
            int len = row.length;
            nState[l] = new int[len];
            System.arraycopy(row, 0, nState[l], 0, len);
        }
        if((state[i][j] == 2 || state[i][j] == 4) && (state[i-1][j-1] == 1 || state[i-1][j-1] == 3) && state[i-2][j-2] == 0){
            nState[i][j] = 0;
            nState[i-1][j-1] = 0;
            nState[i-2][j-2] = state[i][j];
            if(this.canIScore(nState, "left", i-2, j-2)){
                Checkers t = new Checkers(nState);
                return t.myMoveMyLeft(i-2, j-2, moves);
            } else if(this.canIScore(nState, "right", i-2, j-2)){
                Checkers t = new Checkers(nState);
                return t.myMoveMyRight(i-2, j-2, moves);
            }
        } else if((state[i][j] == 2 || state[i][j] == 4) && state[i-1][j-1] == 0){
            nState[i][j] = 0;
            nState[i-1][j-1] = state[i][j];
        }
        else {throw new Exception();}
        for(int k = 0; k < moves.size(); k++){
            if(Arrays.deepEquals(nState, moves.get(k))){
                throw new Exception();
            }
        }
        return nState;
    }
    
    public int[][] myKingMyRight(int i, int j, ArrayList<int[][]> moves) throws Exception {
        if(moves == null)
            moves = new ArrayList<>();
        int[][] nState = new int[state.length][];
        for(int l = 0;l<state.length;l++){
            int[] row = state[l];
            int len = row.length;
            nState[l] = new int[len];
            System.arraycopy(row, 0, nState[l], 0, len);
        }
        if(state[i][j] == 4 && 
                (state[i+1][j+1] == 1 || state[i+1][j+1] == 3) && state[i+2][j+2] == 0){
            nState[i+2][j+2] = state[i][j];
            nState[i+1][j+1] = 0;
            nState[i][j] = 0;
            if(this.canMyKingScore(nState, "left", i+2, j+2)){
                Checkers t = new Checkers(nState);
                return t.myKingMyLeft(i+2, j+2, moves);
            } else if(this.canMyKingScore(nState, "right", i+2, j+2)){
                Checkers t = new Checkers(nState);
                return t.myKingMyRight(i+2, j+2, moves);
            }
        } else if(state[i][j] == 4 && state[i+1][j+1] == 0){
            nState[i+1][j+1] = 4;
            nState[i][j] = 0;
        }
        else {throw new Exception();}
        for(int k = 0; k < moves.size(); k++){
            if(Arrays.deepEquals(nState, moves.get(k))){
                throw new Exception();
            }
        }
        return nState;
    }
    
    public int[][] myKingMyLeft(int i,int j, ArrayList<int[][]> moves) throws Exception {
        if(moves == null)
            moves = new ArrayList<>();
        int[][] nState = new int[state.length][];
        for(int l = 0;l<state.length;l++){
            int[] row = state[l];
            int len = row.length;
            nState[l] = new int[len];
            System.arraycopy(row, 0, nState[l], 0, len);
        }
        if(state[i][j] == 4 
                && (state[i+1][j-1] == 1 || state[i+1][j-1] == 3) && state[i+2][j-2] == 0){
            nState[i][j] = 0;
            nState[i+1][j-1] = 0;
            nState[i+2][j-2] = state[i][j];
            if(this.canMyKingScore(nState, "left", i+2, j-2)){
                Checkers t = new Checkers(nState);
                return t.myKingMyLeft(i+2, j-2, moves);
            } else if(this.canMyKingScore(nState, "right", i+2, j-2)){
                Checkers t = new Checkers(nState);
                return t.myKingMyRight(i+2, j-2, moves);
            }
        } else if(state[i][j] == 4 && state[i+1][j-1] == 0){
            nState[i+1][j-1] = 4;
            nState[i][j] = 0;
        }
        else {throw new Exception();}
        for(int k = 0; k < moves.size(); k++){
            if(Arrays.deepEquals(nState, moves.get(k))){
                throw new Exception();
            }
        }
        return nState;
    }
    
    public ArrayList<int[]> whoCanMove(int player) {
        ArrayList<int[]> locations = new ArrayList<>();
        if(player == 1){
            for(int i = 0;i<state.length;i++){
                for(int j = 0;j<state[i].length;j++){
                    if(this.canOpponentManMove(i,j) || this.canOppKingMove(i,j) ||
                        canOpponentScore(i,j) || this.canOppKingScore(i,j)){
                        int[] pair = {i,j};
                        locations.add(pair);
                    }
                }
            }
        }
        else {
            for(int i = 0;i<state.length;i++){
                for(int j = 0; j< state[i].length;j++){
                    if(this.canIMove(i,j) || this.canMyKingMove(i, j) ||
                            canIScore(i,j) || this.canMyKingScore(i,j)){
                        int[] pair = {i,j};
                        locations.add(pair);
                    }
                }
            }
        }
        return locations;
    }
    
    public int numPossibleMoves(int player, int i, int j){
        int index = 0;
        if(player == 1){
            if(this.canOpponentManMove("left",i,j)) {index++;}
            if(this.canOppKingMove("left",i,j)) {index++;}
            if(this.canOpponentScore("left",i,j)) {index++;}
            if(this.canOppKingScore("left",i,j)) {index++;}
            if(this.canOpponentManMove("",i,j)) {index++;}
            if(this.canOppKingMove("",i,j)) {index++;}
            if(this.canOpponentScore("",i,j)) {index++;}
            if(this.canOppKingScore("",i,j)) {index++;}
        }
        else {
            if(this.canIMove("left",i,j)) {index++;}
            if(this.canMyKingMove("left",i, j)) {index++;}
            if(canIScore("left",i,j)) {index++;}
            if(this.canMyKingScore("left",i,j)) {index++;}
            if(this.canIMove("",i,j)) {index++;}
            if(this.canMyKingMove("",i, j)) {index++;}
            if(canIScore("",i,j)) {index++;}
            if(this.canMyKingScore("",i,j)) {index++;}
        }
        return index;
    }
    
    /**
     * Makes a random move
     * @param player, whose turn it is
     * @return int[][], the board resulting from the random move
     * @throws Exception 
     */
    public int[][] makeAMove(int player) throws Exception{
        ArrayList<int[]> men = this.whoCanMove(player);
        ArrayList<int[][]> moves = new ArrayList<>();
        int randPair = 0;
        Random r = new Random();
        int[][] copy = new int[state.length][];
        for(int l = 0;l<state.length;l++){
            int[] row = state[l];
            int len = row.length;
            copy[l] = new int[len];
            System.arraycopy(row, 0, copy[l], 0, len);
        }
        if(men.size() > 0){
            randPair = r.nextInt(men.size());
            //System.out.println(randPair);
        }
        else {
            System.out.println("THIS HAS NO MOVES");
            System.out.println(this);
        }
        int[][] newState = null;
        if(player == 1){
            int[] pair = men.get(randPair);
            do {
                int rando = r.nextInt(4);
                try{
                    switch (rando) {
                        case 0:
                                newState = this.oppMoveMyLeft(pair[0], pair[1], moves);
                                break;
                        case 1: 
                                newState = this.oppMoveMyRight(pair[0], pair[1], moves);//, copy);
                                break;
                        case 2: 
                                newState = this.oppKingMyLeft(pair[0], pair[1], moves);//, copy);
                                break;
                        case 3: 
                                newState = this.oppKingMyRight(pair[0], pair[1], moves);//, copy);
                                break;
                    }
                } catch(Exception f) {}
            } while(newState == null);
        }
        else {
            int[] pair = men.get(randPair);
            do {
                int rando = r.nextInt(4);
                try{
                    switch (rando) {
                        case 0:
                            newState = this.myMoveMyLeft(pair[0], pair[1], moves);
                            break;
                        case 1: 
                                newState = this.myMoveMyRight(pair[0], pair[1], moves);
                                break;
                        case 2: 
                                newState = this.myKingMyLeft(pair[0], pair[1], moves);
                                break;
                        case 3: 
                                newState = this.myKingMyRight(pair[0], pair[1], moves);
                                break;
                    }
                } catch(Exception f) {}
            } while(newState == null);
        }
        //System.out.println("SUCCESS");
        this.king(newState);
        return newState;
    }
    
    /**
     * Method that returns a list of all of the states that could result from
     * moving a piece
     * @param player
     * @param i, the row
     * @param j, the column
     * @return ArrayList<int[][]>
     */
    public ArrayList<int[][]> tryMoves(int player, int i, int j) {
        ArrayList<int[][]> moves = new ArrayList<>();
        int[][] copy = new int[state.length][];
        for(int l = 0;l<state.length;l++){
            int[] row = state[l];
            int len = row.length;
            copy[l] = new int[len];
            System.arraycopy(row, 0, copy[l], 0, len);
        }
        if(player == 1){
            for (int p = 0; p < numPossibleMoves(1, i, j); p++) {
                int[][] newState = null;
                try {
                    newState = this.oppKingMyLeft(i, j, moves);//, copy);
                } catch (Exception l) {
                    try {
                        newState = this.oppKingMyRight(i, j, moves);//,copy);
                    } catch (Exception m) {
                        try {
                            newState = this.oppMoveMyLeft(i, j, moves);
                        } catch (Exception e) {
                            try {
                                newState = this.oppMoveMyRight(i, j, moves);//,copy);
                            } catch (Exception d) {
                                newState = new int[state.length][];
                                for(int x = 0;x<state.length;x++){
                                    int[] row = state[x];
                                    int len = row.length;
                                    newState[x] = new int[len];
                                    System.arraycopy(row, 0, newState[x], 0, len);
                                }
                            }
                            }
                        }
                    }
                    moves.add(newState);
            }
        }
        else{
            for (int y = 0; y < numPossibleMoves(2, i, j); y++) {
                int[][] newState = null;
                try {
                    newState = this.myMoveMyLeft(i, j, moves);
                } catch (Exception e) {
                    try {
                        newState = this.myMoveMyRight(i, j, moves);
                    } catch (Exception d) {
                        try {
                            newState = this.myKingMyLeft(i, j, moves);
                        } catch (Exception f) {
                            try {
                                newState = this.myKingMyRight(i, j, moves);
                            } catch (Exception g) {
                                newState = new int[state.length][];
                                for(int x = 0;x<state.length;x++){
                                    int[] row = state[x];
                                    int len = row.length;
                                    newState[x] = new int[len];
                                    System.arraycopy(row, 0, newState[x], 0, len);
                                }
                            }
                        }
                    }
                }
                moves.add(newState);
            }
        }
        for(int[][] board : moves){
            king(board);
        }
        return moves;
    }

    public void setState(int[][] state) {
        this.state = state;
    }
    
    /*public int[][] makeGoodMove() throws Exception{
        ArrayList<int[]> men = this.whoCanMove(1);
        ArrayList<int[][]> moves = new ArrayList<>();
        int randPair = 0;
        Random r = new Random();
        if(men.size() > 0){
            randPair = r.nextInt(men.size());
        }
        else {
            System.out.println("THIS HAS NO MOVES");
            System.out.println(this);
        }
        int[][] newState = null;/*new int[6][];
        for(int l = 0;l<6;l++){
            int[] row = state[l];
            int len = row.length;
            newState[l] = new int[len];
            System.arraycopy(row, 0, newState[l], 0, len);
        }
        int[] pair = men.get(randPair);
        if(this.canOppKingScore("left", pair[0], pair[1])){
            newState = this.oppKingMyLeft(pair[0], pair[1], moves);
        } else if(this.canOppKingScore("right", pair[0], pair[1])){
            newState = this.oppKingMyRight(pair[0], pair[1], moves);
        } else if(this.canOpponentScore("left", pair[0], pair[1])){
            newState = this.oppMoveMyLeft(pair[0], pair[1], moves);
        } else if(this.canOpponentScore("right", pair[0], pair[1])){
            newState = this.oppMoveMyRight(pair[0], pair[1], moves, );
        } else if(this.canOppKingMove("left", pair[0], pair[1])){
            newState = this.oppKingMyLeft(pair[0], pair[1], moves);
        } else if(this.canOppKingMove("right", pair[0], pair[1])){
            newState = this.oppKingMyRight(pair[0], pair[1], moves);
        } else if(this.canOpponentManMove("left", pair[0], pair[1])){
            newState = this.oppMoveMyLeft(pair[0], pair[1], moves);
        } else if(this.canOpponentManMove("right", pair[0], pair[1])){
            newState = this.oppMoveMyRight(pair[0], pair[1], moves);
        } 
        return newState;
    }*/
    
    /**
     * Method that simulates a random game
     * @param lastMoved, decides who goes first
     * @return the value of the outcome
     * @throws Exception 
     */
    public int play(int lastMoved) throws Exception {
        Checkers ch = new Checkers(this.getState());
        while(!ch.isOver()) {
            int[][] move = ch.makeAMove(this.toggleMove(lastMoved));
            ch.setState(move);
            lastMoved = this.toggleMove(lastMoved);
        }
        return ch.getValue();
    }
    
    public int toggleMove(int lastMoved){
        if(lastMoved <= 1){
            return 2;
        } else{
            return 1;
        }
    }
    
    public int[] getLoc(int[][] wanted){
        int l = 0;
        int m = 0;
        for(int i = 5; i > -1; i--){
            for(int j = 5;j>-1;j--){
                if((state[i][j] == 2 || state[i][j] == 4) && wanted[i][j] == 0){
                    l = i;
                    m = j;
                }
            }
        }
        int[] loc = {l,m};
        return loc;
    }
    
    public MonteNode userMove(int x, int y, String move) throws Exception {
        int[][] nState = new int[state.length][];
        for(int l = 0;l<state.length;l++){
            int[] row = state[l];
            int len = row.length;
            nState[l] = new int[len];
            System.arraycopy(row, 0, nState[l], 0, len);
        }
        int[][] newState = null;
        ArrayList<int[][]> b = new ArrayList<>();
        MonteNode mn = null;
        //try{
        if(move.contains("k") && move.contains("r")){
            newState = this.oppKingMyRight(y,x,b);//,nState);
        } else if(move.contains("k") && move.contains("l")){
            newState = this.oppKingMyLeft(y,x,b);//,nState);
        } else if(move.contains("l")){
            newState = this.oppMoveMyLeft(y,x,b);//,nState);
        } else if(move.contains("r")){
            newState = this.oppMoveMyRight(y,x,b);//,nState);
        } 
        
        
            //Method m  = this.getClass().getDeclaredMethod(action, clargs);
            //int[][] newState = (int[][]) m.invoke(this, y, x, b, nState);
            Checkers check = new Checkers(newState);
            check.king();
            mn = new MonteNode(check, 1);
        /*} catch(Exception e){
            System.out.println("please try again");
            System.out.println(this);
            //this.userMove(x, y,);
         }*/
        return mn;
    }
    
    @Override
    public String toString() {
        String arr = "";
        for(int[] row : state){
            arr = arr + Arrays.toString(row) + "\n";
        }
        return arr;
    }
}
    
    
    
    
    
    

