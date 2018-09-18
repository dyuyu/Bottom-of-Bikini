import java.util.Observable;

/**
 * @author anthony-pc
 */
public class GameEventObservable extends Observable
{

    public GameEventObservable()
    {
    }

    @Override
    protected synchronized void setChanged()
    {
        super.setChanged();
    }

}
