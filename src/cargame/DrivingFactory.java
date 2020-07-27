package cargame;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.ExpireCleanComponent;
import com.almasb.fxgl.dsl.components.LiftComponent;
import com.almasb.fxgl.dsl.components.OffscreenCleanComponent;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.dsl.views.ScrollingBackgroundView;
import com.almasb.fxgl.entity.*;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.entity.components.IrremovableComponent;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
                .viewWithBBox(new Rectangle(20, 80, Color.BLACK))
                .collidable()
                .with(new OffscreenCleanComponent())
                .with(new LiftComponent().yAxisSpeedDuration(150, Duration.seconds(15)))
                .build();
    }
}
