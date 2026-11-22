#ifndef GOREV2_SENSOR_H
#define GOREV2_SENSOR_H

#include <stdint.h>

typedef struct {
    uint32_t timestamp_ms;
    double pressure_pa;
    float altitude_m;
    float acceleration_x_mps2;
    float acceleration_y_mps2;
    float acceleration_z_mps2;
} SensorMeasurement;

#endif /* GOREV2_SENSOR_H */
