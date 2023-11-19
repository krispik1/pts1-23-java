package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TableArea {
    List<TileSource> tileSources;
    public TableArea() {
        tileSources = new ArrayList<>();
        tileSources.add(new TableCenter());
    }
    public void addFactories(List<TileSource> factories){
        tileSources.addAll(factories);
    }
    public Collection<Tile> take(int sourceIdx, int idx){
        Tile[] tiles = Tile.values();
        if(sourceIdx < tileSources.size()){
            return tileSources.get(sourceIdx).take(tiles[idx]);
        }
        throw new ArrayIndexOutOfBoundsException();
    }
    public boolean isRoundEnd(){
        for(TileSource tileSource : tileSources){
            if(!tileSource.isEmpty()){
                return false;
            }
        }
        return true;
    }
    public void startNewRound(){
        for(TileSource tileSource : tileSources){
            tileSource.startNewRound();
        }
    }
    public String state(){
        String toReturn = "";
        for(int i = 0; i < tileSources.size(); i++){
            if(tileSources.get(i) instanceof TableCenter){
                toReturn += "TableCenter: " + tileSources.get(i).state() + "\n";
            } else{
                toReturn += "Factory: " + tileSources.get(i).state() + "\n";
            }
        }
        return toReturn;
    }
}