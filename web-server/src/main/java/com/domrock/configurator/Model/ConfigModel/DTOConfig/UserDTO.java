package com.domrock.configurator.Model.ConfigModel.DTOConfig;

import com.domrock.configurator.Model.ConfigModel.Company;
import com.domrock.configurator.Model.ConfigModel.Permission;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String email;
    private String name;
    private String password;
    private Permission permission; // Assuming auuuuuuuu
    private Company companyCnpj;

}
//    @Id
//    @Size(max = 255)
//    @Column(name = "email", nullable = false)
//    private String email;
//
//    @Size(max = 255)
//    @NotNull
//    @Column(name = "name", nullable = false)
//    private String name;
//
//    @Size(max = 255)
//    @NotNull
//    @Column(name = "password", nullable = false)
//    private String password;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "permission_id")
//    private Permission permission;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "company_cnpj")
//    private Company companyCnpj;