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

public class DrivingFactory implements EntityFactory {
    @Spawns("car")
    public Entity spawnCar(SpawnData data) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        return entityBuilder()
                .type(CAR)
                .from(data)
                .viewWithBBox(texture("car.png", 70, 140))
                .with(new CollidableComponent(true))
                .with(new IrremovableComponent())
                .build();
    }

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

    //copying moose spawn for pothole
    @Spawns("potHole")
    public Entity spawnPotHole(SpawnData data) {
        return entityBuilder()
                .from(data)
                .type(POTHOLE)
                .viewWithBBox(texture("moose.png", 40, 58)) //TODO ADD CORRECT ART
                .with(new CollidableComponent(true))
                .with(new OffscreenCleanComponent())
                .with(new LiftComponent().yAxisSpeedDuration(150, Duration.seconds(15)))
                .build();
    }

    //copying moose spawn for traffic pylon
    @Spawns("pylon")
    public Entity spawnPylon(SpawnData data) {
        return entityBuilder()
                .from(data)
                .type(PYLON)
                .viewWithBBox(texture("moose.png", 40, 58)) //TODO ADD CORRECT ART
                .with(new CollidableComponent(true))
                .with(new OffscreenCleanComponent())
                .with(new LiftComponent().yAxisSpeedDuration(150, Duration.seconds(15)))
                .build();
    }

    //copying for wrench
    @Spawns("wrench")
    public Entity spawnWrench(SpawnData data) {
        return entityBuilder()
                .from(data)
                .type(WRENCH)
                .viewWithBBox(texture("moose.png", 40, 58)) //TODO ADD CORRECT ART
                .with(new CollidableComponent(true))
                .with(new OffscreenCleanComponent())
                .with(new LiftComponent().yAxisSpeedDuration(150, Duration.seconds(15)))
                .build();
    }

    //copying for pointsorb
    @Spawns("pointsorb")
    public Entity spawnPointsOrb(SpawnData data) {
        return entityBuilder()
                .from(data)
                .type(POINTSORB)
                .viewWithBBox(texture("moose.png", 40, 58)) //TODO ADD CORRECT ART
                .with(new CollidableComponent(true))
                .with(new OffscreenCleanComponent())
                .with(new LiftComponent().yAxisSpeedDuration(150, Duration.seconds(15)))
                .build();
    }
}
