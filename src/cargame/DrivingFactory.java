package cargame;

import com.almasb.fxgl.dsl.components.LiftComponent;
import com.almasb.fxgl.dsl.components.OffscreenCleanComponent;
import com.almasb.fxgl.entity.*;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.entity.components.IrremovableComponent;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import javafx.util.Duration;
import static cargame.EntityType.*;
import static com.almasb.fxgl.dsl.FXGL.*;

/**
 * A factory class to spawn entities into the game. Contains methods for each entity used.
 */
public class DrivingFactory implements EntityFactory {

    /**
     * a method to spawn in the player as the main car
     * @param data
     * @return
     */
    @Spawns("car")
    public Entity spawnCar(SpawnData data) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        return entityBuilder()
                .type(CAR)
                .from(data)
                .viewWithBBox(texture(MainMenu.getSelectedCarAsset() , 70, 140))
                .with(new CollidableComponent(true))
                .with(new IrremovableComponent())
                .build();
    }

    /**
     * a method to spawn in a moose, the main enemy. Will cause a game over on collision.
     * @param data
     * @return
     */
    @Spawns("moose")
    public Entity spawnMoose(SpawnData data) {
        return entityBuilder()
                .from(data)
                .type(MOOSE)
                .viewWithBBox(texture("moose.png", 40, 58))
                .with(new CollidableComponent(true))
                .with(new OffscreenCleanComponent())
                .with(new LiftComponent().yAxisSpeedDuration(150, Duration.seconds(15)))
                .build();
    }

    /**
     * a method to spawn a pothole, an obstacle that reduces health on collision.
     * @param data
     * @return
     */
    //copying moose spawn for pothole
    @Spawns("potHole")
    public Entity spawnPotHole(SpawnData data) {
        return entityBuilder()
                .from(data)
                .type(POTHOLE)
                .viewWithBBox(texture("pothole.png", 40, 58))
                .with(new CollidableComponent(true))
                .with(new OffscreenCleanComponent())
                .with(new LiftComponent().yAxisSpeedDuration(150, Duration.seconds(10)))
                .build();
    }

    /**
     * a method to spawn a pylon, an obstacle that reduces health on collision.
     * @param data
     * @return
     */
    //copying moose spawn for traffic pylon
    @Spawns("pylon")
    public Entity spawnPylon(SpawnData data) {
        return entityBuilder()
                .from(data)
                .type(PYLON)
                .viewWithBBox(texture("pylon.png", 40, 58))
                .with(new CollidableComponent(true))
                .with(new OffscreenCleanComponent())
                .with(new LiftComponent().yAxisSpeedDuration(150, Duration.seconds(15)))
                .build();
    }

    /**
     * a method to spawn a wrench, an obstacle that increases health on collision.
     * @param data
     * @return
     */
    //copying for wrench
    @Spawns("wrench")
    public Entity spawnWrench(SpawnData data) {
        return entityBuilder()
                .from(data)
                .type(WRENCH)
                .viewWithBBox(texture("wrench.png", 40, 58))
                .with(new CollidableComponent(true))
                .with(new OffscreenCleanComponent())
                .with(new LiftComponent().yAxisSpeedDuration(150, Duration.seconds(15)))
                .build();
    }

    /**
     * a method to spawn a points orb, an obstacle that increases score on collision.
     * @param data
     * @return
     */
    //copying for pointsorb
    @Spawns("pointsorb")
    public Entity spawnPointsOrb(SpawnData data) {
        return entityBuilder()
                .from(data)
                .type(POINTSORB)
                .viewWithBBox(texture("pointorb.png", 40, 58))
                .with(new CollidableComponent(true))
                .with(new OffscreenCleanComponent())
                .with(new LiftComponent().yAxisSpeedDuration(150, Duration.seconds(15)))
                .build();
    }
}
