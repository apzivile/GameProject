package lt.baltictalents.clearingdarkness.tools;

import lt.baltictalents.clearingdarkness.entityManager.AnimationManager;
import lt.baltictalents.clearingdarkness.entityManager.Enemy;
import lt.baltictalents.clearingdarkness.entityManager.ShotManager;

public class CollisionManager {

    private final AnimationManager playerAnimated;
    private final Enemy enemy;
    private final ShotManager shotManager;

    public CollisionManager(AnimationManager playerAnimated, Enemy enemy, ShotManager shotManager) {
        this.playerAnimated = playerAnimated;
        this.enemy = enemy;
        this.shotManager = shotManager;
    }

    public void handleCollision(){
//        handlePlayerShot();
        handleEnemyShot();
    }

//    private void handlePlayerShot() {
//        if (shotManager.enemyShotTouches(spaceshipAnimated.getBoundingBox()))
//            spaceshipAnimated.setDead(true);
//
//    }
//    private void handleEnemyShot() {
//        if (shotManager.playerShotTouches(playerAnimated.getBoundingBox()))
//            playerAnimated.setDead(true);
//    }
//private void handlePlayerShot() {
//    if (shotManager.enemyShotTouches(playerAnimated.getBoundingBox()))
//        playerAnimated.setDead(true);
//
//}

    private void handleEnemyShot() {
        if (shotManager.playerShotTouches(enemy.getBoundingBox()))
            enemy.hit();
    }


}
